package com.gameshop.domain.cart;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long user_id;

    @Column
    private Long product_id;

    @Column
    private int quantity;

    @Column
    private Long img_num;

    @Builder
    public Cart (Long user_id, Long product_id, int quantity, Long img_num) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.img_num = img_num;
    }


}
