package com.gameshop.domain.qnas;

import com.gameshop.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Qnas extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QNAS_ID")
    private Long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String reply_state;

    @Column
    private Long img_num;

    @Column
    @OneToMany(mappedBy = "qnas", cascade = CascadeType.ALL)
    private List<Comment> comments;


    @Builder
    public Qnas(String title, String author, String content, String reply_state, Long img_num) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.reply_state = reply_state;
        this.img_num = img_num;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
