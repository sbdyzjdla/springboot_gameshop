package com.gameshop.domain.order.dto;

import com.gameshop.domain.cart.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderListResponse {
    private Long id;
    private Long user_id;
    private String manufact;
    private int p_price;
    private String p_name;
    private Long img_num;
    private int quantity;
    private int total_price;

    @Builder
    public OrderListResponse(Cart entity) {
        this.id = entity.getId();
        this.user_id = entity.getUser_id();
        this.quantity = entity.getCartProducts().get(0).getQuantity();
        this.manufact = entity.getCartProducts().get(0).getProducts().getManufact();
        this.p_price = entity.getCartProducts().get(0).getProducts().getP_price();
        this.p_name = entity.getCartProducts().get(0).getProducts().getP_name();
        this.img_num = entity.getCartProducts().get(0).getProducts().getImg_num();
        this.total_price = p_price * quantity;
    }
}
