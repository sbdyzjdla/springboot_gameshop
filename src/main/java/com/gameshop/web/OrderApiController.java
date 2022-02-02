package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.CartProducts;
import com.gameshop.domain.order.*;
import com.gameshop.domain.order.delivery.Delivery;
import com.gameshop.domain.order.delivery.DeliveryResponseDto;
import com.gameshop.domain.order.delivery.DeliverySaveDto;
import com.gameshop.service.CartService;
import com.gameshop.service.OrderService;
import com.gameshop.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderService orderService;
    private final CartService cartService;
    private final ProductsService productsService;

    @PostMapping("/order/delivery/save")
    public DeliveryResponseDto delivery_save(@ModelAttribute DeliverySaveDto requestDto, @RequestParam int amount,
                                      @LoginUser SessionUser user, Model model) {
        ModelAndView mv = new ModelAndView("jsonView");
        Order order = orderService.find_order_ready(user.getId());
        if(order.getOrder_price() == amount) {
            System.out.println("검증결과 주문 금액과 같습니다");

            //상품 수량변경 및 주문상세 등록
            List<CartProducts> cartProductsList = cartService.findByOrderId(order.getId());
            for(CartProducts cartProducts : cartProductsList) {
                productsService.updateQuantity(cartProducts.getProducts().getId(), cartProducts.getQuantity());
                OrderDetail orderDetail = OrderDetail.builder()
                        .products(cartProducts.getProducts())
                        .order(order)
                        .quantity(cartProducts.getQuantity())
                        .build();
                orderDetail.setOrderPrice(cartProducts.getProducts());
                orderService.OrderDetailSave(orderDetail);
            }

            //주문상세 등록


            //배송지, 주문자정보 등록
            Address address = new Address(requestDto.getPostcode(), requestDto.getAddress(), requestDto.getDetailAddress(),
                    requestDto.getExtraAddress());

            Recipient recipient = new Recipient(requestDto.getOrder_name(), requestDto.getPhone_first(),
                    requestDto.getPhone_second(), requestDto.getPhone_third());
            Delivery delivery = orderService.delivery_save(order, address, recipient);
            order.setDelivery(delivery);
            order.setOrderStatus(OrderStatus.ORDER);

            DeliveryResponseDto responseDto = DeliveryResponseDto.builder()
                    .postcode(delivery.getAddress().getPostcode())
                    .address(delivery.getAddress().getAddress())
                    .detailAddress(delivery.getAddress().getDetailAddress())
                    .extraAddress(delivery.getAddress().getExtraAddress())
                    .order_id(delivery.getOrder().getId())
                    .order_name(delivery.getRecipient().getOrder_name())
                    .phone_first(delivery.getRecipient().getPhone_first())
                    .phone_second(delivery.getRecipient().getPhone_second())
                    .phone_third(delivery.getRecipient().getPhone_third())
                    .delivery_status(delivery.getDeliveryStatus().toString())
                    .build();
            mv.addObject("delivery", responseDto);
            return responseDto;
        } else {
            System.out.println("검증결과 주문 금액과 다릅니다.");
            return null;
        }


    }


}
