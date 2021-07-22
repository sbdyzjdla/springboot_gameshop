package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.dto.CartListResponseDto;
import com.gameshop.domain.cart.dto.CartProdListResDto;
import com.gameshop.domain.products.consoles.dto.ConsolesResponseDto;
import com.gameshop.domain.products.dto.ProductsOrderResponseDto;
import com.gameshop.domain.products.dto.ProductsResponseDto;
import com.gameshop.service.*;
import com.gameshop.web.dto.QnasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final QnasService qnasService;
    private final FilesService filesService;
    private final ConsolesService consolesService;
    private final ProductsService productsService;
    private final CartService cartService;
    private final OrderService orderService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        return "index";
    }

    @GetMapping("/board")
    public String board(Model model, @LoginUser SessionUser user){
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        return "board";
    }

    @GetMapping("/board/write_board")
    public String write_board(Model model, @LoginUser SessionUser user){
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
        }
        return "write_board";
    }

    @GetMapping("/board/view_board/{id}")
    public String view_board(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        QnasResponseDto dto = qnasService.findById(id);
        model.addAttribute("qnas", dto);
        return "view_board";
    }

    @GetMapping("/board/update_board/{id}")
    public String update_board(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        QnasResponseDto dto = qnasService.findById(id);
        model.addAttribute("qnas", dto);
        return "update_board";
    }

    @GetMapping("/products/hardware-nintendo")
    public String hardware_nintendo(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        return "hardware-nintendo";
    }

    @GetMapping("/products/hardware-ps5")
    public String hardware_ps5(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        return "hardware-ps5";
    }

    @GetMapping("/products/software-switch")
    public String software_switch(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        return "software-switch";
    }
    @GetMapping("/products/software-ps5")
    public String software_ps5(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        return "software-ps5";
    }
    @GetMapping("/products/view/{id}")
    public String view_products(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        //ConsolesResponseDto dto = consolesService.findById(id);
        ProductsResponseDto dto = productsService.findById(id);
        model.addAttribute("dto", dto);
        return "view_products";
    }

    @GetMapping("/cart")
    public String cart(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        List<CartProdListResDto> cartList = cartService.findAllCartUser(user);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    @GetMapping("/order")
    public String order(Model model, @LoginUser SessionUser user, @RequestParam(value = "list_checked")List<String> list_checked) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        int total_price = 0;
        List<CartListResponseDto> cartList = new ArrayList<>();

        Long ready_count = orderService.find_order_ready(user.getId());
        if(ready_count != null) {
            orderService.del_order_ready(user.getId());
        }

        for(String list_id : list_checked) {
            Long cart_id = Long.parseLong(list_id);
            Cart cart = cartService.findById(cart_id);
            //cartList.add(new CartListResponseDto(cartService.findById(cart_id)));
            cartList.add(new CartListResponseDto(cart));
            orderService.order_ready(cart, user.getId());
            total_price += cart.getCartProducts().get(0).getOrderPrice();
        }
        System.out.println("전체가격 !!!" + total_price);

        model.addAttribute("orderList", cartList);


        return "order";
    }

    @GetMapping("/order/one/{id}/{quantity}")
    public String order(Model model, @LoginUser SessionUser user, @PathVariable Long id, @PathVariable int quantity) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        ProductsOrderResponseDto dto = productsService.findByIdOrder(id, quantity);
        model.addAttribute("orderList", dto);

        return "order";
    }

    @GetMapping("/order/confirm")
    public String orderConfirm(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo", userInfo);
            if (user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        return "order_confirm";
    }

    @GetMapping("/admin")
    public String admin_page(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        return "admin";
    }

    @GetMapping("/about")
        public String about(Model model, @LoginUser SessionUser user) {
            if(user != null) {
                List<SessionUser> userInfo = new ArrayList<>();
                userInfo.add(user);

                model.addAttribute("userInfo" , userInfo);
                if(user.getRole().equals("ROLE_ADMIN")) {
                    model.addAttribute("admin", "admin");
                    //return "admin";
                }
            }
            return "about";
    }

}
