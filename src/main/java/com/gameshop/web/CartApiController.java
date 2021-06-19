package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.dto.CartListResponseDto;
import com.gameshop.domain.cart.dto.CartSaveRequestDto;
import com.gameshop.domain.consoles.dto.ConsolesResponseDto;
import com.gameshop.domain.products.Products;
import com.gameshop.domain.user.User;
import com.gameshop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CartApiController {

    private final CartService cartService;
    private final FilesService filesService;
    private final ConsolesService consolesService;
    private final ProductsService productsService;
    private final UserService userService;

    @PostMapping("/cart/save")
    public Long save(HttpServletRequest request, @LoginUser SessionUser user) {

        Long id = Long.parseLong(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        Products products = productsService.findById(id);
//        ConsolesResponseDto dto = consolesService.findById(id);
//
        CartSaveRequestDto requestDto = CartSaveRequestDto.builder()
                .user_id(user.getId())
                .products(products)
                .quantity(quantity)
                .build();

        return cartService.save(requestDto);
    }

//    @GetMapping("/cart/cartList")
//    public List<CartListResponseDto> findAllDesc(@LoginUser SessionUser user) { return cartService.findAllDesc(user); }
}
