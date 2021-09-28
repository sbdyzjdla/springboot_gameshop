package com.gameshop.service;

import com.gameshop.domain.qnas.*;
import com.gameshop.domain.user.User;
import com.gameshop.domain.user.UserRepository;
import com.gameshop.web.dto.CommentResponseDto;
import com.gameshop.web.dto.CommentSaveRequestDto;
import com.gameshop.web.dto.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final QnasRepository qnasRepository;
    private final CommentRepositorySupport commentRepositorySupport;

    @Transactional
    public Long save(CommentSaveRequestDto requestDto) {
        Qnas qna = qnasRepository.findById(requestDto.getQnas_id())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다"));
        User user = userRepository.findById(requestDto.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다"));
        Comment comment =  Comment.builder()
                .content(requestDto.getContent())
                .user(user)
                .qnas(qna)
                .build();
        return commentRepository.save(comment).getId();
    }

    @Transactional
    public Long update(Long id, String content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        comment.update(content);
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
        commentRepository.delete(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAllQnas(Long qnas_id) {
        return commentRepositorySupport.findAllQnas(qnas_id);
    }
}
