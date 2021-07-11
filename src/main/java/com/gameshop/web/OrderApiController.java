package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderService orderService;

//    @PostMapping("/order/temp/save")
//    public Long orderTempSave(@LoginUser SessionUser user, HttpServletRequest request) {
//        String[] save_list = request.getParameterValues("list_checked[]");
//        for(String num : save_list) {
//            orderService.orderTempSave(Long.parseLong(num), user.getId());
//        }
//        return 1L;
//    }
}
