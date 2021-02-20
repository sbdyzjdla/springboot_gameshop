package com.gameshop.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QnasUpdateRequestDto {

    private String title;
    private String content;

    @Builder
    public QnasUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }
}
