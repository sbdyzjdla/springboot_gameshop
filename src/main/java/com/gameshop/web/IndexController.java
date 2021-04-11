package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.consoles.dto.ConsolesListResponseDto;
import com.gameshop.domain.consoles.dto.ConsolesResponseDto;
import com.gameshop.domain.user.User;
import com.gameshop.service.ConsolesService;
import com.gameshop.service.FilesService;
import com.gameshop.service.QnasService;
import com.gameshop.web.dto.FilesResponseDto;
import com.gameshop.web.dto.QnasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final QnasService qnasService;
    private final FilesService filesService;
    private final ConsolesService consolesService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            System.out.println("ROLE!!" + user.getRole());
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
        }
        QnasResponseDto dto = qnasService.findById(id);
        model.addAttribute("qnas", dto);
        return "update_board";
    }

    @GetMapping("/hardware-nintendo")
    public String hardware_nintendo(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
//            List<ConsolesListResponseDto> dto = consolesService.findAllNint();
//            model.addAttribute("ninList", dto);
        }
        return "hardware-nintendo";
    }

    @GetMapping("/software-switch")
    public String software_switch(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
        }
        return "software-switch";
    }
    @GetMapping("/products/view/{id}")
    public String view_products(@PathVariable Long id, Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
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
        }
        return "cart";
    }

    @GetMapping("/admin")
    public String admin_page(Model model, @LoginUser SessionUser user) {
        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);

            model.addAttribute("userInfo" , userInfo);
        }
        return "admin";
    }
}
