package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.CartProducts;
import com.gameshop.domain.order.*;
import com.gameshop.domain.order.delivery.Delivery;
import com.gameshop.domain.order.delivery.DeliveryResponseDto;
import com.gameshop.domain.order.delivery.DeliverySaveDto;
import com.gameshop.domain.order.dto.OrderConfirmResponseDto;
import com.gameshop.domain.order.dto.OrderDetailResponseDto;
import com.gameshop.service.CartService;
import com.gameshop.service.OrderService;
import com.gameshop.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * OrderApiController - 주문관리 컨트롤러
 */

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderService orderService;
    private final CartService cartService;
    private final ProductsService productsService;

    /**
     * 주문관리 -
     * @param requestDto
     * @param amount
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/order/delivery/save")
    public OrderConfirmResponseDto delivery_save(@ModelAttribute DeliverySaveDto requestDto, @RequestParam int amount,
                                      @LoginUser SessionUser user, Model model) {
        ModelAndView mv = new ModelAndView("jsonView");
        Order order = orderService.find_order_ready(user.getId());
        if(order.getOrder_price() == amount) {
            System.out.println("검증결과 주문 금액과 같습니다");

            //상품 수량변경 및 주문상세 등록
            List<CartProducts> cartProductsList = cartService.findByOrderId(order.getId());
            String orderTitle = "";
            int idx = 0;
            for(CartProducts cartProducts : cartProductsList) {
                productsService.updateQuantity(cartProducts.getProducts().getId(), cartProducts.getQuantity());
                OrderDetail orderDetail = OrderDetail.builder()
                        .products(cartProducts.getProducts())
                        .order(order)
                        .quantity(cartProducts.getQuantity())
                        .build();
                orderDetail.setOrderPrice(cartProducts.getProducts());
                orderService.OrderDetailSave(orderDetail);
                if(idx == 0) {
                    order.setOrder_img(cartProducts.getProducts().getImg_num());
                }
                if(idx == cartProductsList.size()-1) {
                    orderTitle += cartProducts.getProducts().getP_name();
                } else {
                    orderTitle += (cartProducts.getProducts().getP_name()+ ",");
                }
                idx++;
            }

            // 주문제목이 길어지면 ...로 마무리
            if(orderTitle.length() > 30) {
                orderTitle = orderTitle.substring(0,30)+ "...";
            }
            order.setOrder_title(orderTitle);

            //배송지, 주문자정보 등록
            Address address = new Address(requestDto.getPostcode(), requestDto.getAddress(), requestDto.getDetailAddress(),
                    requestDto.getExtraAddress());

            Recipient recipient = new Recipient(requestDto.getOrder_name(), requestDto.getPhone_first(),
                    requestDto.getPhone_second(), requestDto.getPhone_third());
            Delivery delivery = orderService.delivery_save(order, address, recipient);
            order.setDelivery(delivery);
            order.setOrderStatus(OrderStatus.ORDER);

            OrderConfirmResponseDto order_confirm = orderService.order_confirm(order.getId());
            model.addAttribute("order_confirm", order_confirm);
            return order_confirm;
        } else {
            System.out.println("검증결과 주문 금액과 다릅니다.");
            return null;
        }


    }
}
