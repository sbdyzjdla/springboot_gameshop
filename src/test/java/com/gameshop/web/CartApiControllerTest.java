package com.gameshop.web;

import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.CartRepository;
import com.gameshop.domain.cart.CartRepositorySupport;
import com.gameshop.domain.consoles.ConsolesRepository;
import com.gameshop.domain.consoles.dto.ConsolesSaveRequestDto;
import com.gameshop.domain.products.Products;
import com.gameshop.domain.user.Role;
import com.gameshop.domain.user.User;
import com.gameshop.domain.user.UserRepository;
import com.gameshop.service.CartService;
import com.gameshop.service.ConsolesService;
import com.gameshop.service.ProductsService;
import com.gameshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartApiControllerTest {

    @Autowired
    private ConsolesService consolesService;
    @Autowired
    private ConsolesRepository consolesRepository;
    @Autowired
    private ProductsService productsService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepositorySupport cartRepositorySupport;

    @Test
    @WithMockUser(roles = "USER")
    @Transactional   // join컬럼의 fetch type이 LAZY 일때 could not initialize proxy - no session 오류발생해서 단위테스트에 붙임
    public void 장바구니_추가() throws Exception {
        //given
        String manufact = "테스트 제조사명";
        int p_price = 420000;
        int quantity = 100;
        Products products1 = createProducts(manufact, "상품1", p_price, quantity);
        Products products2 = createProducts(manufact, "상품2", p_price, quantity);
        //수량
        int p1_quantity = 10;
        int p2_quantity = 20;

        //유저 세팅
        User findUser = User.builder()
                .name("test유저")
                .email("test이메일")
                .role(Role.USER)
                .build();
        userRepository.save(findUser);

        //when

        Long cartNum = cartService.save(products1, p1_quantity, findUser.getId());
        cartNum = cartService.save(products2, p2_quantity, findUser.getId());

        //then
        List<Cart> findAll = cartRepositorySupport.findAllUser(findUser.getId());
        assertThat(findAll.get(0).getCartProducts().get(0).getProducts().getP_name()).isEqualTo("상품1");
        assertThat(findAll.get(1).getCartProducts().get(0).getProducts().getP_name()).isEqualTo("상품2");
        assertThat(findAll.get(0).getUser_id()).isEqualTo(findUser.getId());
    }


    private Products createProducts(String manufact, String p_name, int p_price, int quantity) throws Exception{
        ConsolesSaveRequestDto requestDto = ConsolesSaveRequestDto.builder()
                .manufact(manufact)
                .p_name(p_name)
                .p_price(p_price)
                .quantity(quantity)
                .build();
        Long consoleNum = consolesService.save(requestDto);
        return productsService.findById(consoleNum);
    }

}