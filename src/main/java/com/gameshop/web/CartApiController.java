package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.dto.CartListResponseDto;
import com.gameshop.domain.products.Products;
import com.gameshop.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

        return cartService.save(products, quantity, user.getId());
    }

    @PutMapping("/cart/update/{id}")
    public Long update(@PathVariable Long id, HttpServletRequest request) {
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        return cartService.update(id, quantity);
    }

    @PutMapping("/cart/del/")
    public Long del(HttpServletRequest request) {
        String[] del_list = request.getParameterValues("list_checked[]");
        for(String num : del_list) {
            cartService.del(Long.parseLong(num));
        }
        return 1L;
    }

    @GetMapping("/cart/cartList")
    public List<CartListResponseDto> findAllUser(@LoginUser SessionUser user) { return cartService.findAllUser(user); }
}
