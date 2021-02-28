package com.gameshop.web;

import com.gameshop.service.QnasService;
import com.gameshop.web.dto.QnasResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
