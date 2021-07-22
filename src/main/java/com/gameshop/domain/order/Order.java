package com.gameshop.domain.order;

import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.CartProducts;
import com.gameshop.domain.delivery.Delivery;
import com.gameshop.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ORDER_TABLE")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    private Cart cart;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private Long user_id;

    @Builder
    public Order(Cart cart, Long user_id, OrderStatus orderStatus) {
        this.cart = cart;
        this.user_id = user_id;
        this.orderStatus = orderStatus;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for(CartProducts cartProducts : cart.getCartProducts()) {
            totalPrice += cartProducts.getOrderPrice();
        }
        return totalPrice;
    }
}
