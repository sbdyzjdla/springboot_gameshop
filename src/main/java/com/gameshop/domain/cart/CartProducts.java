package com.gameshop.domain.cart;

import com.gameshop.domain.order.Order;
import com.gameshop.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class CartProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARTPRODUCTS_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCTS_ID")
    private Products products;

    private int p_price;
    private int orderPrice;
    private int quantity;

    @Builder
    public CartProducts (Products products, int p_price, int orderPrice, int quantity) {
        this.products = products;
        this.p_price = p_price;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public void update(int quantity) {
        this.quantity = quantity;
        this.orderPrice = this.p_price * quantity;
    }

}
