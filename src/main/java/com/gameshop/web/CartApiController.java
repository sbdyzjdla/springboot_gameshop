package com.gameshop.web;

import com.gameshop.domain.cart.dto.CartSaveRequestDto;
import com.gameshop.service.CartService;
import com.gameshop.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class CartApiController {

    private final CartService cartService;
    private final FilesService filesService;

    @PostMapping("/cart/save")
    public Long save(HttpServletRequest request) {

        String temp = request.getParameter("id");
        System.out.println("아이디!!" + temp);
        //return cartService.save(requestDto);
        return 0L;
    }
}
