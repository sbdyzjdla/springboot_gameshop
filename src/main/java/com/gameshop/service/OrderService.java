package com.gameshop.service;

import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.order.Order;
import com.gameshop.domain.order.OrderRepository;
import com.gameshop.domain.order.OrderRepositorySupport;
import com.gameshop.domain.order.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderRepositorySupport orderRepositorySupport;

    @Transactional
    public Long order_ready(Cart cart, Long user_id) {

        Long ready_count = orderRepositorySupport.find_order_ready(user_id);
        if(ready_count != null) {
            orderRepositorySupport.del_order_ready(user_id);
        }
        Order entity = Order.builder()
                .cart(cart)
                .user_id(user_id)
                .orderStatus(OrderStatus.READY)
                .build();
        return orderRepository.save(entity).getId();
    }

}
