package com.gameshop.domain.consoles.dto;

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
    private String edition;
    private Long c_price;
    private MultipartFile consoles_img;
    private Long img_num;

    @Builder
    public ConsolesUpdateRequestDto (String manufact, String edition, Long c_price) {
        this.manufact = manufact;
        this.edition = edition;
        this.c_price = c_price;
    }
}
