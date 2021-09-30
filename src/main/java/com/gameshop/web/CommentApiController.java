package com.gameshop.web;

import com.gameshop.service.CommentService;
import com.gameshop.web.dto.CommentResponseDto;
import com.gameshop.web.dto.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @GetMapping("/comment/{qnas_id}")
    public List<CommentResponseDto> findAllQnas(@PathVariable Long qnas_id) {
        return commentService.findAllQnas(qnas_id);
    }

    @DeleteMapping("/comment/{id}")
    public Long delete(@PathVariable Long id) {
        return commentService.delete(id);
    }
}
