package com.gameshop.domain.cart.dto;

import com.gameshop.domain.cart.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class CartSaveRequestDto {

    private Long user_id;
    private Long product_id;
    private int quantity;
    private Long img_num;

    @Builder
    public CartSaveRequestDto (Long user_id, Long product_id, int quantity, Long img_num) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.img_num = img_num;
    }

    public Cart toEntity() {
        return Cart.builder()
                .user_id(user_id)
                .product_id(product_id)
                .quantity(quantity)
                .img_num(img_num)
                .build();
    }
}
