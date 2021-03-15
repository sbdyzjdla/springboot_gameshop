package com.gameshop.web;

import com.gameshop.config.auth.LoginUser;
import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.user.User;
import com.gameshop.service.QnasService;
import com.gameshop.web.dto.QnasResponseDto;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {

        if(user != null) {
            List<SessionUser> userInfo = new ArrayList<>();
            userInfo.add(user);
            model.addAttribute("userInfo" , userInfo);
            System.out.println("ROLE!!" + user.getRole());
            if(user.getRole().equals("ROLE_ADMIN")) {
                return "admin";
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
    public String write_board(){

        return "write_board";
    }

    @GetMapping("/board/view_board/{id}")
    public String view_board(@PathVariable Long id, Model model) {
        QnasResponseDto dto = qnasService.findById(id);
        model.addAttribute("qnas", dto);
        return "view_board";
    }

    @GetMapping("/board/update_board/{id}")
    public String update_board(@PathVariable Long id, Model model) {
        QnasResponseDto dto = qnasService.findById(id);
        model.addAttribute("qnas", dto);
        return "update_board";
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
}
