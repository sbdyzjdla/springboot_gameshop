package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.products.Products;
import com.gameshop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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


        return cartService.save(products, quantity, user.getId());
    }

//    @GetMapping("/cart/cartList")
//    public List<CartListResponseDto> findAllDesc(@LoginUser SessionUser user) { return cartService.findAllDesc(user); }
}
