package com.gameshop.web;

import com.gameshop.service.QnasService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final QnasService qnasService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/board")
    public String board(){

        return "board";
    }

    @GetMapping("/board/write_board")
    public String write_board(){

        return "write_board";
    }
}
