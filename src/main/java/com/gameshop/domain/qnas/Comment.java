package com.gameshop.domain.qnas;

import com.gameshop.domain.BaseTimeEntity;
import com.gameshop.domain.user.User;
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

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "QNAS_ID")
    private Qnas qnas;

    @Builder
    public Comment(String content, User user, Qnas qnas) {
        this.content = content;
        this.user = user;
        this.qnas = qnas;
    }
    public void update(String content) {
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setQnas(Qnas qnas) {
        this.qnas = qnas;
    }
}
