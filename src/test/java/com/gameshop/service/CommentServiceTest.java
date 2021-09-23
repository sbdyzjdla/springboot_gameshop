package com.gameshop.service;

import com.gameshop.domain.qnas.Comment;
import com.gameshop.domain.qnas.CommentRepository;
import com.gameshop.domain.qnas.Qnas;
import com.gameshop.domain.qnas.QnasRepository;
import com.gameshop.domain.user.Role;
import com.gameshop.domain.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentServiceTest {

    @Autowired
    private QnasRepository qnasRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void 댓글작성() throws Exception {
        //given
        // 1. 게시글
        Qnas qnas = Qnas.builder()
                    .author("테스트유저")
                    .content("테스트게시글")
                    .reply_state("미답변")
                    .title("테스트제목")
                    .build();
        qnasRepository.save(qnas);
        // 2. 유저
        User user = User.builder()
                .name("테스트유저1")
                .role(Role.USER)
                .email("bb")
                .build();
        User admin = User.builder()
                .name("관리자")
                .role(Role.ADMIN)
                .email("aa")
                .build();
        Comment comment1 = Comment.builder()
                .qnas(qnas)
                .user(user)
                .content("질문")
                .build();
        Comment comment2 = Comment.builder()
                .qnas(qnas)
                .user(admin)
                .content("답변")
                .build();
        //when
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        //then
        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList.get(0).getContent()).isEqualTo("질문");
        assertThat(commentList.get(1).getContent()).isEqualTo("답변");
    }
}