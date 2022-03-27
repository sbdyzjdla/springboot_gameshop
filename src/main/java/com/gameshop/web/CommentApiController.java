package com.gameshop.web;

import com.gameshop.service.CommentService;
import com.gameshop.domain.qnas.dto.CommentResponseDto;
import com.gameshop.domain.qnas.dto.CommentSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * CommentApiController - 댓글관리 컨트롤러
 */

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    /**
     * 댓글관리 - 댓글등록
     * @param requestDto
     * @return
     */
    @PostMapping("/comment")
    public Long save(@RequestBody CommentSaveRequestDto requestDto) {
        return commentService.save(requestDto);
    }

    /**
     * 댓글관리 - 댓글수정
     * @param request
     * @return
     */
    @PutMapping("/comment")
    public Long update(HttpServletRequest request) {
        String content = request.getParameter("content");
        Long id = Long.parseLong(request.getParameter("id"));

        return commentService.update(id, content);
    }

    /**
     * 댓글관리 - 게시글의 댓글조회
     * @param qnas_id
     * @return
     */
    @GetMapping("/comment/{qnas_id}")
    public List<CommentResponseDto> findAllQnas(@PathVariable Long qnas_id) {
        return commentService.findAllQnas(qnas_id);
    }

    /**
     * 댓글관리 - 댓글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/comment/{id}")
    public Long delete(@PathVariable Long id) {
        return commentService.delete(id);
    }
}
