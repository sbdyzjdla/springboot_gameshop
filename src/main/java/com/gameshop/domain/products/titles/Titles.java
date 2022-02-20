package com.gameshop.domain.products.titles;

import com.gameshop.domain.products.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Titles extends Products {

    private String console;

    @Builder
    public Titles(String console, String manufact, String p_name, int p_price, Long img_num, int quantity ) {
        this.setConsole(console);
        this.setManufact(manufact);
        this.setP_name(p_name);
        this.setP_price(p_price);
        this.setImg_num(img_num);
        this.setQuantity(quantity);
    }

    public void update(String console, String manufact, String p_name, int p_price, int quantity) {
        this.setConsole(console);
        this.setManufact(manufact);
        this.setP_name(p_name);
        this.setP_price(p_price);
        this.setQuantity(quantity);
    }
}
