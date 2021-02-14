package com.gameshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {

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
