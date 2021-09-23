package com.gameshop.web.dto;

import lombok.Getter;

@Getter
public class CommentSaveDto {

    private Long qnas_id;
    private Long user_id;
    private String content;
}
