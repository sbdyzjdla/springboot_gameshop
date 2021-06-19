package com.gameshop.domain.cart;

import com.gameshop.domain.products.Products;
import com.gameshop.domain.user.User;
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
    @Column(name = "CART_ID")
    private Long id;

    @Column
    private Long user_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCTS_ID")
    private Products products;

    private int quantity;

    @Builder
    public Cart (Long user_id, Products products, int quantity){
        this.user_id = user_id;
        this.products = products;
        this.quantity = quantity;
    }

    //    @Column
//    private Long user_id;
//
//    @Column
//    private Long product_id;
//
//    @Column
//    private int quantity;
//
//    @Column
//    private Long img_num;
//
//    @Column
//    private String p_name;

//    @Builder
//    public Cart (Long user_id, Long product_id, int quantity, Long img_num, String p_name) {
//        this.user_id = user_id;
//        this.product_id = product_id;
//        this.quantity = quantity;
//        this.img_num = img_num;
//        this.p_name = p_name;
//    }


}
