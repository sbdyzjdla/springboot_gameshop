package com.gameshop.service;

import com.gameshop.domain.qnas.Comment;
import com.gameshop.domain.qnas.CommentRepository;
import com.gameshop.domain.qnas.Qnas;
import com.gameshop.domain.qnas.QnasRepository;
import com.gameshop.domain.user.Role;
import com.gameshop.domain.user.User;
import com.gameshop.domain.user.UserRepository;
import com.gameshop.web.dto.CommentSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    private static Qnas qnas;
    private static User user;
    private User admin;
    private CommentSaveRequestDto requestDto1;
    private CommentSaveRequestDto requestDto2;
    private Long qnas_id;

    @BeforeEach
    public void 게시글_유저_셋팅() throws Exception {
        // 1. 게시글
        qnas = Qnas.builder()
                .author("테스트유저")
                .content("테스트게시글1")
                .reply_state("미답변")
                .title("테스트제목")
                .build();
        qnas_id = qnasRepository.save(qnas).getId();
        // 2. 유저
        user = User.builder()
                .name("테스트유저1")
                .role(Role.USER)
                .email("bbb@naver.com")
                .build();
        admin = User.builder()
                .name("관리자")
                .role(Role.ADMIN)
                .email("aaa@gmail.com")
                .build();
        Long user1_id = userRepository.save(user).getId();
        Long user2_id = userRepository.save(admin).getId();
        // 3. 댓글
        requestDto1 = CommentSaveRequestDto.builder()
                .qnas_id(qnas_id)
                .user_id(user1_id)
                .content("질문")
                .build();
        requestDto2 = CommentSaveRequestDto.builder()
                .qnas_id(qnas_id)
                .user_id(user2_id)
                .content("답변")
                .build();
    }

    @AfterEach
    public void 데이터삭제() {
        qnasRepository.deleteAll();
        userRepository.deleteAll();
        commentRepository.deleteAll();
    }

    @Test
    public void 댓글작성() throws Exception {
        //given
//        Comment comment1 = Comment.builder()
//                .qnas(qnas)
//                .user(user)
//                .content("질문")
//                .build();
//        Comment comment2 = Comment.builder()
//               .qnas(qnas)
//                .user(admin)
//                .content("답변")
//                .build();
        //when
//        commentRepository.save(comment1);
//        commentRepository.save(comment2);
        commentService.save(requestDto1);
        commentService.save(requestDto2);

        //then
        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList.get(0).getContent()).isEqualTo("질문");
        assertThat(commentList.get(1).getContent()).isEqualTo("답변");
    }

    @Test
    public void 내용수정() throws Exception {
        //given
        Long id = commentService.save(requestDto1);
        //when
        commentService.update(id, "변경");

        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList.get(0).getContent()).isEqualTo("변경");
    }

    @Test
    public void 댓글삭제() throws Exception {
        //given
        Long id = commentService.save(requestDto1);
        //when
        commentService.delete(id);
        //then

        List<Comment> commentList = commentRepository.findAll();
        assertThat(commentList.size()).isEqualTo(0);
    }

    @Test
    public void 댓글불러오기() throws Exception {
        //given
        commentService.save(requestDto1);
        commentService.save(requestDto2);
        qnas = Qnas.builder()
                .author("테스트유저")
                .content("테스트게시글2")
                .reply_state("미답변")
                .title("테스트제목")
                .build();
        Long qnas2_id = qnasRepository.save(qnas).getId();
        //when
        List<Comment> commentList1 = commentService.findAllQnas(qnas_id);
        List<Comment> commentList2 = commentService.findAllQnas(qnas2_id);
        //then
        assertThat(commentList1.size()).isEqualTo(2);
        assertThat(commentList2.size()).isEqualTo(0);
    }
}