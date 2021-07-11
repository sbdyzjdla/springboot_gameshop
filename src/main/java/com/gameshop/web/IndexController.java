package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.dto.CartListResponseDto;
import com.gameshop.domain.products.consoles.dto.ConsolesResponseDto;
import com.gameshop.service.CartService;
import com.gameshop.service.ConsolesService;
import com.gameshop.service.FilesService;
import com.gameshop.service.QnasService;
import com.gameshop.web.dto.QnasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final QnasService qnasService;
    private final FilesService filesService;
    private final ConsolesService consolesService;
    private final CartService cartService;

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
        ConsolesResponseDto dto = consolesService.findById(id);
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
        List<CartListResponseDto> cartList = cartService.findAllUser(user);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    @GetMapping("/order")
    public String order(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            if(user.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("admin", "admin");
                //return "admin";
            }
        }

        List<CartListResponseDto> cartList = cartService.findAllUser(user);
        model.addAttribute("cartList", cartList);


        return "order";
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
