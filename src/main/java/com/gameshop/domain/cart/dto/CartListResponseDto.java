package com.gameshop.domain.cart.dto;

import com.gameshop.domain.cart.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class CartListResponseDto {

    private Long id;
    private Long user_id;
    private Long product_id;
    private int quantity;
    private Long img_num;

    @Builder
    public CartListResponseDto(Cart entity) {
        this.id = entity.getId();
        this.user_id = entity.getUser_id();
        this.product_id = entity.getProduct_id();
        this.quantity = entity.getQuantity();
        this.img_num = entity.getImg_num();
    }
}
