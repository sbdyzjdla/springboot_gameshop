package com.gameshop.domain.order;

import com.gameshop.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "ORDER_DETAIL")
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_DETAIL_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "PRODUCTS_ID")
    private Products products;

    private int orderPrice;
    private int quantity;

    public void setOrderPrice(Products products) {
        this.orderPrice = products.getP_price() * quantity;
    }

    @Builder
    public OrderDetail(Order order, Products products, int quantity) {
        this.order = order;
        this.products = products;
        this.quantity = quantity;
    }
}
