package com.gameshop.domain.products.titles.dto;

import com.gameshop.domain.products.titles.Titles;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TitlesResponseDto {

    private Long id;
    private String console;
    private String manufact;
    private String p_name;
    private int p_price;
    private int quantity;
    private Long img_num;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public TitlesResponseDto(Titles entity) {
        this.id = entity.getId();
        this.console = entity.getConsole();
        this.manufact = entity.getManufact();
        this.p_name = entity.getP_name();
        this.p_price = entity.getP_price();
        this.quantity = entity.getQuantity();
        this.img_num = entity.getImg_num();
    }
}
