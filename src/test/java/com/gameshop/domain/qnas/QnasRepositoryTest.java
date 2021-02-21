package com.gameshop.domain.qnas;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QnasRepositoryTest {

    @Autowired
    QnasRepository qnasRepository;

    @After
    public void cleanup() {
        qnasRepository.deleteAll();
    }

    @Test
    public void qna저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String author = "테스트 작성자";
        String content = "테스트 작성내용";
        String reply_state = "답변완료";

        qnasRepository.save(Qnas.builder()
                .title(title)
                .author(author)
                .content(content)
                .reply_state(reply_state)
                .build());

        //when
        List<Qnas> qnasList = qnasRepository.findAll();

        //then
        Qnas qnas = qnasList.get(0);
        assertThat(qnas.getTitle()).isEqualTo(title);
        assertThat(qnas.getAuthor()).isEqualTo(author);
        assertThat(qnas.getContent()).isEqualTo(content);
        assertThat(qnas.getReply_state()).isEqualTo(reply_state);
    }

    @Test
    public void BaseTimeEntity () {
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        qnasRepository.save(Qnas.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Qnas> qnasList = qnasRepository.findAll();

        //then
        Qnas qnas = qnasList.get(0);

        System.out.println(">>>>>>>>>>> createDate = " + qnas.getCreatedDate()+"," +
                "modifiedDate = " + qnas.getModifiedDate());
        assertThat(qnas.getCreatedDate()).isAfter(now);
        assertThat(qnas.getModifiedDate()).isAfter(now);
    }
}