package com.gameshop.domain.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartProdListResDto {

    private Long cart_products_id;
    private Long cart_id;
    private int p_price;
    private int order_price;
    private String p_name;
    private Long img_num;
    private int quantity;

}
