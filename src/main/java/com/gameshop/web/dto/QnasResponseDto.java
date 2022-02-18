package com.gameshop.web.dto;

import com.gameshop.domain.qnas.Qnas;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class QnasResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private String reply_state;
    private Long img_num;
    private String createdDate;
    private String modifiedDate;

    public QnasResponseDto(Qnas entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();
        this.reply_state = entity.getReply_state();
        this.img_num = entity.getImg_num();
        this.createdDate = entity.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
