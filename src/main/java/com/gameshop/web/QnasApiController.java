package com.gameshop.web;

import com.gameshop.web.dto.QnasSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QnasApiController {

    private final QnasService qnasService;

    @PostMapping("/api/v1/qnas")
    public Long save(@RequestBody QnasSaveRequestDto requestDto) {
        return qnasService.save(requestDto);
    }
}
