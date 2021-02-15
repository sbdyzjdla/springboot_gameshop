package com.gameshop.web.dto;

import com.gameshop.domain.qnas.Qnas;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QnasSaveRequestDto {

    private String title;
    private String author;
    private String content;
    private String reply_state;

    @Builder
    public QnasSaveRequestDto(String title, String author, String content, String reply_state) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.reply_state = reply_state;
    }

    public Qnas toEntity() {
        return Qnas.builder()
                .title(title)
                .author(author)
                .content(content)
                .reply_state(reply_state)
                .build();
    }
}
