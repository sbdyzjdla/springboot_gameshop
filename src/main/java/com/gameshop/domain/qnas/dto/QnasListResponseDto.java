package com.gameshop.domain.qnas.dto;

import com.gameshop.domain.qnas.Qnas;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QnasListResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private String reply_state;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public QnasListResponseDto(Qnas entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
        this.reply_state = entity.getReply_state();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
