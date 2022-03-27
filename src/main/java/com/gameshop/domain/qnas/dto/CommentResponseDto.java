package com.gameshop.domain.qnas.dto;

import com.gameshop.domain.user.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long comment_id;
    private LocalDateTime comment_date;
    private String content;
    private Long user_id;
    private String email;
    private String name;
    private String picture;
    private Role role;
}
