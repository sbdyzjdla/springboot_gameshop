package com.gameshop.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameshop.domain.consoles.Consoles;
import com.gameshop.domain.consoles.ConsolesRepository;
import com.gameshop.domain.consoles.dto.ConsolesListResponseDto;
import com.gameshop.domain.consoles.dto.ConsolesSaveRequestDto;
import com.gameshop.service.ConsolesService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsolesApiControllerTest {
    // @ExtendWith(SpringExtension.class : Junit5 //@RunWith(SpringRunner.class) Junit4 와 유사
    // Junit5 에서는 접근제어자가 public -> default
    // Before, After -> + Each

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ConsolesService service;

    @Autowired
    private ConsolesRepository repository;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();;
    }

    @WithMockUser(roles = "ADMIN")
    @Test
    void 기기추가() throws Exception{
        //given
        String manufact = "제조업체";
        String p_name = "제품명";
        int p_price = 420000;
        int quantity = 10;

        // !!단위테스트 이기 때문에 절대 값을 비워둬서는 안됨 따라서 더미데이터 생성
        MockMultipartFile file = new MockMultipartFile("user-file", "test.txt",
                null, "test data".getBytes());
        ConsolesSaveRequestDto requestDto = ConsolesSaveRequestDto.builder()
                .manufact(manufact)
                .p_name(p_name)
                .p_price(p_price)
                .quantity(quantity)
                .consoles_img(file)
                .build();

        String url = "http://localhost:" + port + "/admin/consoles/save";
        //when
//        mvc.perform(post(url)
//                .contentType(MediaType.MULTIPART_FORM_DATA)
//                .accept(MediaType.MULTIPART_FORM_DATA)
//            )



        //then
    }

//    @WithMockUser
//    @Test
//    void 콘솔등록() throws Exception{
//        //given
//        String manufact = "제조업체";
//        String p_name = "제품명";
//        int p_price = 420000;
//        int quantity = 10;
//        Long img_num = 1L;
//        ConsolesSaveRequestDto dto = ConsolesSaveRequestDto.builder()
//                .manufact(manufact)
//                .p_name(p_name)
//                .p_price(p_price)
//                .quantity(quantity)
//                .build();
//
//        String url = "http://localhost:" + port + "/admin/consoles/save";
//
//        dto.setImg_num(img_num);
//        //when
//        ResponseEntity<Long> responseEntity = restTemplate
//                .postForEntity(url, dto, Long.class);
//
////        mvc.perform(post(url)
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(new ObjectMapper().writeValueAsString(dto)))
////                .andExpect(status().isOk());
//
//        //then
//        //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FOUND);
//        //200은 정상  302 found 요청을 완료하기 위해 추가 동작이 필요
//        //assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        //assertThat(responseEntity.getBody()).isGreaterThan(1L);
//
//        List<Consoles> consolesList = repository.findAllDesc();
//        System.out.println("consolesList : " + consolesList);
//        //assertThat(consolesList.get(0).getManufact()).isEqualTo(manufact);
//
//    }
//
//    @Test
//    void findAllDesc() {
//    }
//
//    @Test
//    void findById() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void findAllNint() {
//    }
}