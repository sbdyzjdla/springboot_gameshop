package com.gameshop.service;

import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.order.*;
import com.gameshop.domain.order.delivery.Delivery;
import com.gameshop.domain.order.delivery.DeliveryRepository;
import com.gameshop.domain.order.delivery.DeliveryStatus;
import com.gameshop.domain.order.dto.OrderConfirmListResponse;
import com.gameshop.domain.order.dto.OrderConfirmResponseDto;
import com.gameshop.domain.order.dto.OrderDetailResponseDto;
import com.gameshop.domain.order.dto.OrderListResponse;
import com.gameshop.domain.products.consoles.dto.ConsolesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderRepositorySupport orderRepositorySupport;
    private final DeliveryRepository deliveryRepository;
    private final OrderDetailRepository orderDetailRepository;

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
//
//    @Transactional
//    public int order_ready_total_price(Long user_id) {
//        int total_price = 0;
//
//        List<Order> orders = new ArrayList<>();
//        orders = orderRepositorySupport.find_order_ready_total_price(user_id);
//        for(Order order : orders) {
//            total_price += order.getCart().getCartProducts().get(0).getOrderPrice();
//        }
//        return total_price;
//    }

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

    @Transactional
    public OrderConfirmResponseDto order_confirm(Long id) {
        OrderConfirmResponseDto dto = orderRepositorySupport.order_confirm(id);
        dto.setDelivery_status(OrderConfirmResponseDto.enumToValue(dto.getDeliveryStatus()));
        dto.setOrder_date(dto.getModified_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return dto;
    }

    @Transactional
    public Long OrderDetailSave(OrderDetail entity) {
        return orderDetailRepository.save(entity).getId();
    }

    @Transactional
    public List<OrderConfirmListResponse> orderList(Long user_id) {
        return orderRepositorySupport.orderList(user_id).stream()
                .map(OrderConfirmListResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<OrderDetailResponseDto> orderDetailProductList(Long order_id) {
        return orderRepositorySupport.orderDetailProductList(order_id);
    }
}
