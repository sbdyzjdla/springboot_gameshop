package com.gameshop.domain.consoles.dto;

import com.gameshop.domain.consoles.Consoles;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConsolesResponseDto {

    private Long id;
    private String manufact;
    private String edition;
    private int p_price;
    private int quantity;
    private Long img_num;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ConsolesResponseDto(Consoles entity) {
        this.id = entity.getId();
        this.manufact = entity.getManufact();
        this.edition = entity.getEdition();
        this.p_price = entity.getP_price();
        this.quantity = entity.getQuantity();
        this.img_num = entity.getImg_num();
    }

}
