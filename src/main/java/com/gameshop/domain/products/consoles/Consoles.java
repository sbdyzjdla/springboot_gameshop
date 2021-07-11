package com.gameshop.domain.products.consoles;

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
