package com.gameshop.web;

import com.gameshop.service.CommentService;
import com.gameshop.web.dto.CommentSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public Long save(@RequestBody CommentSaveDto requestDto) {
        return commentService.save(requestDto);
    }
}
