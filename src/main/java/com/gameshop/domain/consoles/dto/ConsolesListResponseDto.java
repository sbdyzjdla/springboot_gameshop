package com.gameshop.domain.consoles.dto;

import com.gameshop.domain.consoles.Consoles;
import com.gameshop.domain.qnas.Qnas;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
public class ConsolesListResponseDto {

    private Long id;
    private String manufact;
    private String p_name;
    private int p_price;
    private int quantity;
    private Long img_num;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ConsolesListResponseDto(Consoles entity) {
        this.id = entity.getId();
        this.manufact = entity.getManufact();
        this.p_name = entity.getP_name();
        this.p_price = entity.getP_price();
        this.quantity = entity.getQuantity();
        this.img_num = entity.getImg_num();
    }
}
