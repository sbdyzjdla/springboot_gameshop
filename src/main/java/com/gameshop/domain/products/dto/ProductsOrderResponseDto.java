package com.gameshop.domain.products.dto;

import com.gameshop.domain.products.Products;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductsOrderResponseDto {

    private Long id;
    private String manufact;
    private String p_name;
    private int p_price;
    private int quantity;
    private Long img_num;
    private int total_price;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ProductsOrderResponseDto(Products entity) {
        this.id = entity.getId();
        this.manufact = entity.getManufact();
        this.p_name = entity.getP_name();
        this.p_price = entity.getP_price();
        this.quantity = entity.getQuantity();
        this.img_num = entity.getImg_num();
        this.total_price = entity.getP_price() * entity.getQuantity();
    }
}
