package com.gameshop.domain.qnas;

import com.gameshop.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENTS_ID")
    private Long id;
    private String author;
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "QNAS_ID")
    private Qnas qnas;

    @Builder
    public Comment(String author, String content) {
        this.author = author;
        this.content = content;
    }
    public void update(String content) {
        this.content = content;
    }
}
