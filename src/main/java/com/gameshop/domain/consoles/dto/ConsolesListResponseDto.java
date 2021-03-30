package com.gameshop.domain.consoles.dto;

import com.gameshop.domain.consoles.Consoles;
import com.gameshop.domain.qnas.Qnas;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
public class ConsolesListResponseDto {

    private String manufact;
    private String edition;
    private Long c_price;
    private Long img_num;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ConsolesListResponseDto(Consoles entity) {
        this.manufact = entity.getManufact();
        this.edition = entity.getEdition();
        this.c_price = entity.getC_price();
    }
}
