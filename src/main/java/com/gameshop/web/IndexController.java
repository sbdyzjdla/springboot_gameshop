package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.CartProducts;
import com.gameshop.domain.cart.dto.CartListResponseDto;
import com.gameshop.domain.cart.dto.CartProdListResDto;
import com.gameshop.domain.order.Order;
import com.gameshop.domain.order.dto.OrderConfirmResponseDto;
import com.gameshop.domain.products.consoles.dto.ConsolesResponseDto;
import com.gameshop.domain.products.dto.ProductsOrderResponseDto;
import com.gameshop.domain.products.dto.ProductsResponseDto;
import com.gameshop.domain.qnas.Qnas;
import com.gameshop.service.*;
import com.gameshop.web.dto.QnasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//    @GetMapping("/board")
//    public String board(Model model, @LoginUser SessionUser user){
//        if(user != null) {
//            List<SessionUser> userInfo = new ArrayList<>();
//            userInfo.add(user);
//
//            model.addAttribute("userInfo" , userInfo);
//            if(user.getRole().equals("ROLE_ADMIN")) {
//                model.addAttribute("admin", "admin");
//                //return "admin";
//            }
//        }
//        return "board";
//    }

    @GetMapping(value = {"/board/{p_num}", "/board/{p_num}/{search}"})
    public String board(Model model, @LoginUser SessionUser user, @PathVariable(required = false) Integer p_num
                , @PathVariable(required = false) String search){
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }
        if(p_num.equals(null)) {
            p_num = 1;
        }

        Page<Qnas> qnasList;
        if(search == null) {
            qnasList = qnasService.findAllPageDesc(p_num);
        } else {
            qnasList = qnasService.findByTitle(search, p_num);
        }

        boolean hasPrev = qnasList.hasPrevious();
        boolean hasNext = qnasList.hasNext();
        boolean hasLast = qnasList.isLast();
        boolean hasFirst = qnasList.isFirst();

        model.addAttribute("qnasList", qnasList.getContent());
        if(hasPrev) {
            model.addAttribute("prev", qnasList.getPageable().getPageNumber());
        }
        if(hasNext) {
            model.addAttribute("next", qnasList.getPageable().getPageNumber() +2);
        }
        if(!hasFirst) {
            model.addAttribute("first", 1);
        }
        if(!hasLast) {
            model.addAttribute("last", qnasList.getTotalPages());
        }
        model.addAttribute("search", search);

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
        List<CartProdListResDto> cartList = new ArrayList<>();

        Long ready_count = orderService.find_order_ready_cnt(user.getId());
        Order order;
        if(ready_count != 0L) {
            order = orderService.find_order_ready(user.getId());
            order.initOrderProducts();
        } else {
            order = orderService.order_ready(user.getId());
        }

        for(String list_id : list_checked) {
            Long cartProduct_id = Long.parseLong(list_id);
            CartProdListResDto cartProducts = cartService.CartProdFindById(cartProduct_id);
            cartList.add(cartProducts);
            CartProducts orderProduct = cartService.cartProdEntityFindById(cartProduct_id);
            orderProduct.setOrder(null);
            order.addOrderProducts(orderProduct);
        }

        for(CartProdListResDto cart : cartList) {
            total_price += cart.getOrder_price();
            order.setOrder_price(total_price);
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

    @GetMapping("/order/confirm/{id}")
    public String orderConfirm(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo", userInfo);
            if (user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
            }
        }
        OrderConfirmResponseDto responseDto = orderService.order_confirm(id);
        if(responseDto.getUser_id() != user.getId()) {
            return HttpStatus.BAD_REQUEST.toString();     //BAD_REQUEST 400에러
        }
        model.addAttribute("order_confirm", responseDto);
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
