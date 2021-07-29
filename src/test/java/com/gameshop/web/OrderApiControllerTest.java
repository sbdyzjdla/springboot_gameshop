package com.gameshop.web;

import com.gameshop.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderApiControllerTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void 배송지저장_주문객체연관관계설정() {
        //given

        //when

        //then
    }
}