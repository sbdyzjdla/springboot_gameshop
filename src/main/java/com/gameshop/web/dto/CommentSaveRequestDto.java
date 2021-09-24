package com.gameshop.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {
    private Long qnas_id;
    private Long user_id;
    private String content;

    @Builder
    public CommentSaveRequestDto(Long qnas_id, Long user_id, String content) {
        this.qnas_id = qnas_id;
        this.user_id = user_id;
        this.content = content;
    }
}
