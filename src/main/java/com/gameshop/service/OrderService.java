package com.gameshop.service;

import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.order.*;
import com.gameshop.domain.order.delivery.Delivery;
import com.gameshop.domain.order.delivery.DeliveryRepository;
import com.gameshop.domain.order.delivery.DeliveryStatus;
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
    private final DeliveryRepository deliveryRepository;

    @Transactional
    public Order order_ready(Long user_id) {
        Order entity = Order.builder()
                .user_id(user_id)
                .orderStatus(OrderStatus.READY)
                .build();
        return orderRepository.save(entity);
    }

    @Transactional
    public Long find_order_ready_cnt(Long user_id) {
        return orderRepositorySupport.find_order_ready_cnt(user_id);
    }

    @Transactional
    public Order find_order_ready(Long user_id) {return orderRepositorySupport.find_order_ready(user_id);}

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

    @Transactional
    public Delivery delivery_save(Order order, Address address, Recipient recipient) {
        Delivery delivery = Delivery.builder()
                .order(order)
                .address(address)
                .recipient(recipient)
                .deliveryStatus(DeliveryStatus.READY)
                .build();
        return deliveryRepository.save(delivery);
    }

}
