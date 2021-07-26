package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.order.Address;
import com.gameshop.domain.order.Order;
import com.gameshop.domain.order.Recipient;
import com.gameshop.domain.order.delivery.Delivery;
import com.gameshop.domain.order.delivery.DeliverySaveDto;
import com.gameshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/order/delivery/save")
    public void delivery_save(@ModelAttribute DeliverySaveDto requestDto, @LoginUser SessionUser user) {
        Address address = new Address(requestDto.getPostcode(), requestDto.getAddress(), requestDto.getDetailAddress(),
                requestDto.getExtraAddress());

        Recipient recipient = new Recipient(requestDto.getOrder_name(), requestDto.getPhone_first(),
                requestDto.getPhone_second(), requestDto.getPhone_third());
        Order order = orderService.find_order_ready(user.getId());
        Delivery delivery = orderService.delivery_save(order, address, recipient);
        order.setDelivery(delivery);
    }


}
