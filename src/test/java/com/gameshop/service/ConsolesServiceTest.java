package com.gameshop.service;

import com.gameshop.domain.products.consoles.Consoles;
import com.gameshop.domain.products.consoles.ConsolesRepository;
import com.gameshop.domain.products.consoles.dto.ConsolesSaveRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConsolesServiceTest {

    @Autowired
    private FilesService filesService;
    @Autowired
    private ConsolesService consolesService;
    @Autowired
    private ConsolesRepository consolesRepository;

    @Test
    public void 기기추가() throws Exception{
        //given
        String manufact = "테스트 제조사명";
        String p_name = "테스트 상품명";
        int p_price = 420000;
        int quantity = 100;


        // !!단위테스트 이기 때문에 절대 값을 비워둬서는 안됨 따라서 더미데이터 생성
        MockMultipartFile file = new MockMultipartFile("user-file", "test.txt",
                null, "test data".getBytes());

        ConsolesSaveRequestDto requestDto = ConsolesSaveRequestDto.builder()
                .manufact(manufact)
                .p_name(p_name)
                .p_price(p_price)
                .quantity(quantity)
                .build();
        requestDto.setImg_num(1L);
        //when
        Long consoleNum = consolesService.save(requestDto);

        //then
        List<Consoles> findAll = consolesRepository.findAll();
        assertThat(findAll.get(0).getManufact()).isEqualTo(manufact);
    }

}