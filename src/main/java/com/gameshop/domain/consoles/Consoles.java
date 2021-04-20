package com.gameshop.domain.consoles;

import com.gameshop.domain.BaseTimeEntity;
import com.gameshop.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Consoles extends Products {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

//    @Column
//    private String manufact;

//    @Column
//    private String edition;

//    @Column
//    private int p_price;
//
//    @Column
//    private int quantity;
//
//    @Column
//    private Long img_num;

    @Builder
    public Consoles(String manufact, String p_name, int p_price, Long img_num, int quantity ) {
        this.setManufact(manufact);
        this.setP_name(p_name);
        this.setP_price(p_price);
        this.setImg_num(img_num);
        this.setQuantity(quantity);
    }

    public void update(String manufact, String p_name, int p_price, int quantity) {
        this.setManufact(manufact);
        this.setP_name(p_name);
        this.setP_price(p_price);
        this.setQuantity(quantity);
    }


}
