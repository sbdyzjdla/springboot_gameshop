package com.gameshop.domain.order;

import com.gameshop.domain.BaseTimeEntity;
import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.CartProducts;
import com.gameshop.domain.order.delivery.Delivery;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "ORDER_TABLE")
public class Order extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private int order_price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private Long user_id;

    @OneToMany(mappedBy = "order")
    private List<CartProducts> orderProducts = new ArrayList<>();

    private String order_title;
    private Long order_img;

    public void addOrderProducts(CartProducts paramCartProd) {
        orderProducts.add(paramCartProd);
        paramCartProd.setOrder(this);
    }

    public void initOrderProducts() {
        orderProducts.clear();
    }

    @Builder
    public Order(Long user_id, OrderStatus orderStatus) {
        this.user_id = user_id;
        this.orderStatus = orderStatus;
    }

    public void update(int order_price) {
        this.order_price = order_price;
    }

    public static Order createOrder(Long user_id, CartProducts... cartProducts) {
        Order order = new Order();
        order.setUser_id(user_id);
        for(CartProducts cartProduct : cartProducts) {
            order.addOrderProducts(cartProduct);
        }
        return order;
    }
}
