package com.gameshop.domain.products.titles.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class TitlesUpdateRequestDto {

    private String console;
    private String manufact;
    private String p_name;
    private int p_price;
    private int quantity;
    private MultipartFile products_img;
    private Long img_num;

    @Builder
    public TitlesUpdateRequestDto (String console, String manufact, String p_name, int p_price, int quantity) {
        this.console = console;
        this.manufact = manufact;
        this.p_name = p_name;
        this.p_price = p_price;
        this.quantity = quantity;
    }
}
