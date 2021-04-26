package com.gameshop.domain.order;

import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.user.User;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

//    private Cart cart;
//    private User user;
}
