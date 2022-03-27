package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.dto.CartListResponseDto;
import com.gameshop.domain.cart.dto.CartProdListResDto;
import com.gameshop.domain.products.Products;
import com.gameshop.service.*;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CartApiController - 장바구니 컨트롤러
 */

@Api(value = "장바구니 컨트롤러")
@RequiredArgsConstructor
@RestController
public class CartApiController {

    private final CartService cartService;
    private final ConsolesService consolesService;
    private final ProductsService productsService;

    /**
     * 장바구니 - 장바구니 추가
     * @param request
     * @param user
     * @return
     */
    @PostMapping("/cart/save")
    public Long save(HttpServletRequest request, @LoginUser SessionUser user) {

        Long id = Long.parseLong(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Products products = productsService.findByIdCart(id);

        return cartService.add(products, quantity, user.getId());
    }

    /**
     * 장바구니 - 장바구니 물품 수량 변경
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/cart/update/{id}")
    public Long update(@PathVariable Long id, HttpServletRequest request) {
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        return cartService.update(id, quantity);
    }

    /**
     * 장바구니 - 장바구니 물품 삭제
     * @param request
     * @return
     */
    @DeleteMapping("/cart/del/")
    public Long del(HttpServletRequest request) {
        String[] del_list = request.getParameterValues("list_checked[]");
        for(String num : del_list) {
            cartService.cartProductsDel(Long.parseLong(num));
        }
        return 1L;
    }

    /**
     * 장바구니 - 장바구니 조회
     * @param user
     * @return
     */
    @GetMapping("/cart/cartList")
    public List<CartProdListResDto> findAllCartUser(@LoginUser SessionUser user) { return cartService.findAllCartUser(user); }


}
