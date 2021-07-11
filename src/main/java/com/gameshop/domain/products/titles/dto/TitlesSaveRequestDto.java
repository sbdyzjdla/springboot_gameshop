package com.gameshop.domain.products.titles.dto;

import com.gameshop.domain.products.titles.Titles;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class TitlesSaveRequestDto {

    private String console;
    private String manufact;
    private String p_name;
    private int p_price;
    private int quantity;
    private MultipartFile products_img;
    private Long img_num;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public TitlesSaveRequestDto(String console ,String manufact, String p_name, int p_price, int quantity,
                                Long img_num, MultipartFile products_img) {
        this.console = console;
        this.manufact = manufact;
        this.p_name = p_name;
        this.p_price = p_price;
        this.quantity = quantity;
        this.img_num = img_num;
        this.products_img = products_img;

    }

    public Titles toEntity() {
        return Titles.builder()
                .console(console)
                .manufact(manufact)
                .p_name(p_name)
                .p_price(p_price)
                .quantity(quantity)
                .img_num(img_num)
                .build();
    }
}
