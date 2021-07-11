package com.gameshop.domain.products.consoles.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ConsolesUpdateRequestDto {

    private String manufact;
    private String p_name;
    private int p_price;
    private int quantity;
    private MultipartFile consoles_img;
    private Long img_num;

    @Builder
    public ConsolesUpdateRequestDto (String manufact, String p_name, int p_price, int quantity) {
        this.manufact = manufact;
        this.p_name = p_name;
        this.p_price = p_price;
        this.quantity = quantity;
    }
}
