package com.gameshop.service;

import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.order.Order;
import com.gameshop.domain.order.OrderRepository;
import com.gameshop.domain.order.OrderRepositorySupport;
import com.gameshop.domain.order.OrderStatus;
import com.gameshop.domain.order.dto.OrderListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderRepositorySupport orderRepositorySupport;

    @Transactional
    public Long order_ready(Cart cart, Long user_id) {
        Order entity = Order.builder()
                .cart(cart)
                .user_id(user_id)
                .orderStatus(OrderStatus.READY)
                .build();
        return orderRepository.save(entity).getId();
    }

    @Transactional
    public Long find_order_ready(Long user_id) {
        return orderRepositorySupport.find_order_ready(user_id);
    }

    @Transactional
    public void del_order_ready(Long user_id) {
        orderRepositorySupport.del_order_ready(user_id);
    }

    @Transactional
    public int order_ready_total_price(Long user_id) {
        int total_price = 0;

        List<Order> orders = new ArrayList<>();
        orders = orderRepositorySupport.find_order_ready_total_price(user_id);
        for(Order order : orders) {
            total_price += order.getCart().getCartProducts().get(0).getOrderPrice();
        }
        return total_price;
    }

}
