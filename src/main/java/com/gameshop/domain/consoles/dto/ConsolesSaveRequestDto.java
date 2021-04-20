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
    private String p_name;
    private int p_price;
    private int quantity;
    private MultipartFile consoles_img;
    private Long img_num;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public ConsolesSaveRequestDto(String manufact, String p_name, int p_price, int quantity, Long img_num) {
        this.manufact = manufact;
        this.p_name = p_name;
        this.p_price = p_price;
        this.quantity = quantity;
        this.img_num = img_num;

    }

    public Consoles toEntity() {
        return Consoles.builder()
                .manufact(manufact)
                .p_name(p_name)
                .p_price(p_price)
                .quantity(quantity)
                .img_num(img_num)
                .build();
    }
}
