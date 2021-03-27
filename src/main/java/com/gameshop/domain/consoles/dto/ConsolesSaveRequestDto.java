package com.gameshop.domain.consoles.dto;

import com.gameshop.domain.consoles.Consoles;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ConsolesSaveRequestDto {

    private String manufact;
    private String edition;
    private Long c_price;
    private MultipartFile consoles_img;
    private Long img_num;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public ConsolesSaveRequestDto(String manufact, String edition, Long c_price, Long img_num) {
        this.manufact = manufact;
        this.edition = edition;
        this.c_price = c_price;
        this.img_num = img_num;

    }

    public Consoles toEntity() {
        return Consoles.builder()
                .manufact(manufact)
                .edition(edition)
                .c_price(c_price)
                .img_num(img_num)
                .build();
    }
}
