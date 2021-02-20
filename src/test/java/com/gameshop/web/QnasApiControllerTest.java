package com.gameshop.web;

import com.gameshop.domain.qnas.Qnas;
import com.gameshop.domain.qnas.QnasRepository;
import com.gameshop.web.dto.QnasSaveRequestDto;
import com.gameshop.web.dto.QnasUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.ws.Response;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QnasApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private QnasRepository qnasRepository;

    @After
    public void tearDown() throws Exception {
        qnasRepository.deleteAll();
    }

    @Test
    public void qna_게시글저장() throws Exception{
        //given
        String title = "테스트 게시글";
        String author = "테스트 작성자";
        String content = "테스트 작성내용";
        String reply_state = "답변완료";
        QnasSaveRequestDto requestDto = QnasSaveRequestDto.builder()
                .title(title)
                .author(author)
                .content(content)
                .reply_state(reply_state)
                .build();

        String url = "http://localhost:" + port + "/api/v1/qnas";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Qnas> all = qnasRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getAuthor()).isEqualTo(author);
        assertThat(all.get(0).getContent()).isEqualTo(content);
        assertThat(all.get(0).getReply_state()).isEqualTo(reply_state);
    }

    @Test
    public void Qnas_update() throws Exception {
        //given
        Qnas saveQnas = qnasRepository.save(Qnas.builder()
                .title("title")
                .content("content")
                .author("author")
                .reply_state("reply_state")
                .build());

        Long updateId = saveQnas.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        QnasUpdateRequestDto requestDto = QnasUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/qnas/" + updateId;
        HttpEntity<QnasUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
                requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Qnas> all = qnasRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}