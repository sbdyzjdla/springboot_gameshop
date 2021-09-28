package com.gameshop.web.dto;

import com.gameshop.domain.user.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long comment_id;
    private LocalDateTime comment_data;
    private String content;
    private Long user_id;
    private String email;
    private String name;
    private String picture;
    private Role role;
}
