package com.gameshop.web;

import com.gameshop.service.CommentService;
import com.gameshop.web.dto.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public Long save(@RequestBody CommentSaveRequestDto requestDto) {
        return commentService.save(requestDto);
    }
    @PutMapping("/comment")
    public Long update(HttpServletRequest request) {
        String content = request.getParameter("content");
        Long id = Long.parseLong(request.getParameter("id"));

        return commentService.update(id, content);
    }
}
