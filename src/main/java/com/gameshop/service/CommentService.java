package com.gameshop.service;

import com.gameshop.domain.qnas.Comment;
import com.gameshop.domain.qnas.CommentRepository;
import com.gameshop.domain.qnas.Qnas;
import com.gameshop.domain.qnas.QnasRepository;
import com.gameshop.domain.user.User;
import com.gameshop.domain.user.UserRepository;
import com.gameshop.web.dto.CommentSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final QnasRepository qnasRepository;

    public Long save(CommentSaveDto requestDto) {
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
}
