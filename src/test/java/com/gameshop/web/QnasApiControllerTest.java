package com.gameshop.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameshop.domain.qnas.Qnas;
import com.gameshop.domain.qnas.QnasRepository;
import com.gameshop.service.FilesService;
import com.gameshop.service.QnasService;
import com.gameshop.web.dto.QnasSaveRequestDto;
import com.gameshop.web.dto.QnasUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QnasApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Autowired
    private QnasService qnasService;
    @Autowired
    private QnasRepository qnasRepository;

    @Autowired
    private FilesService filesService;

    @After
    public void tearDown() throws Exception {
        qnasRepository.deleteAll();
    }

    @WithMockUser(roles = "USER")
    @Test
    public void 게시글검색() throws Exception {

        //given
        String title = "테스트 게시글";
        String author = "테스트 작성자";
        String content = "테스트 작성내용";
        String reply_state = "답변완료";

        String title2 = "테스트 질문";
        String author2 = "테스트 작성자";
        String content2 = "테스트 작성내용";
        String reply_state2 = "답변완료";

        MockMultipartFile file = new MockMultipartFile("user-file", "test.txt",
                null, "test data".getBytes());

        QnasSaveRequestDto requestDto = QnasSaveRequestDto.builder()
                .title(title)
                .author(author)
                .content(content)
                .reply_state(reply_state)
                .qnas_img(file)
                .build();
        QnasSaveRequestDto requestDto1 = QnasSaveRequestDto.builder()
                .title(title2)
                .author(author2)
                .content(content2)
                .reply_state(reply_state2)
                .qnas_img(file)
                .build();
        qnasService.save(requestDto);
        qnasService.save(requestDto1);

        //when
        Page<Qnas> qnasPage1 =  qnasService.findByTitle("게시글", 1);
        Page<Qnas> qnasPage2 = qnasService.findByTitle("문", 1);
        //then
        assertThat(qnasPage1.getContent().get(0).getTitle()).isEqualTo("테스트 게시글");
        assertThat(qnasPage2.getContent().get(0).getTitle()).isEqualTo("테스트 질문");

    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void qna_게시글저장() throws Exception{
        //given
        String title = "테스트 게시글";
        String author = "테스트 작성자";
        String content = "테스트 작성내용";
        String reply_state = "답변완료";

        // !!단위테스트 이기 때문에 절대 값을 비워둬서는 안됨 따라서 더미데이터 생성
        MockMultipartFile file = new MockMultipartFile("user-file", "test.txt",
                null, "test data".getBytes());

        QnasSaveRequestDto requestDto = QnasSaveRequestDto.builder()
                .title(title)
                .author(author)
                .content(content)
                .reply_state(reply_state)
                .qnas_img(file)
                .build();

        String url = "http://localhost:" + port + "/api/v1/qnas";
        System.out.println("오리진 : " + requestDto.getQnas_img().getOriginalFilename());

        /*       TestRestTemplate 쓰는경우
        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        */

        //when        springSecurity 적용할시 테스트 코드
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());


        //modelAttribute는 파라미터값으로 바인딩 하는방식이라 setter 필요
        //따라서 body값이 아닌 파라미터 값으로 요청을 해야함
//        mvc.perform(post(url)
//                .flashAttr("requestDto", requestDto))
//                .andExpect(status().isOk());

//        //then
//        List<Qnas> all = qnasRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getAuthor()).isEqualTo(author);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//        assertThat(all.get(0).getReply_state()).isEqualTo(reply_state);

    }

//    @Test
//    public void Qnas_update() throws Exception {
//        //given
//        Qnas saveQnas = qnasRepository.save(Qnas.builder()
//                .title("title")
//                .content("content")
//                .author("author")
//                .reply_state("reply_state")
//                .build());
//
//        Long updateId = saveQnas.getId();
//        String expectedTitle = "title2";
//        String expectedContent = "content2";
//
//        QnasUpdateRequestDto requestDto = QnasUpdateRequestDto.builder()
//                .title(expectedTitle)
//                .content(expectedContent)
//                .build();
//
//        String url = "http://localhost:" + port + "/api/v1/qnas/" + updateId;
//        HttpEntity<QnasUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
//
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
//                requestEntity, Long.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Qnas> all = qnasRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
//        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
//    }
}