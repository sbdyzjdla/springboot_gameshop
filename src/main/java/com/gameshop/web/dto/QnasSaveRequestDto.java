package com.gameshop.web.dto;

import com.gameshop.domain.qnas.Qnas;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class QnasSaveRequestDto {

    private String title;
    private String author;
    private String content;
    private String reply_state;
    private MultipartFile qnas_img;
    private Long img_num;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public QnasSaveRequestDto(String title, String author, String content, String reply_state, Long img_num) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.reply_state = reply_state;
        this.img_num = img_num;
    }

    public Qnas toEntity() {
        return Qnas.builder()
                .title(title)
                .author(author)
                .content(content)
                .reply_state(reply_state)
                .img_num(img_num)
                .build();
    }
}
