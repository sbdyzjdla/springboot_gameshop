package com.gameshop.domain.cart;

import com.gameshop.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @JoinColumn(name = "PRODUCTS_ID")
    private Products products;

    private int orderPrice;
    private int quantity;

    @Builder
    public CartProducts (Products products, int orderPrice, int quantity) {
        this.products = products;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }
}
