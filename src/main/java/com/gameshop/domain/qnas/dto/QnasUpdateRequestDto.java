package com.gameshop.domain.qnas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class QnasUpdateRequestDto {

    private String title;
    private String content;
    private MultipartFile qnas_img;

    @Builder
    public QnasUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
