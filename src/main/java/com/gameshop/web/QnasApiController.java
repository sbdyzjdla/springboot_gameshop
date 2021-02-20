package com.gameshop.web;

import com.gameshop.service.QnasService;
import com.gameshop.web.dto.QnasSaveRequestDto;
import com.gameshop.web.dto.QnasUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QnasApiController {

    private final QnasService qnasService;

    @PostMapping("/api/v1/qnas")
    public Long save(@RequestBody QnasSaveRequestDto requestDto) {
        return qnasService.save(requestDto);
    }

    @PutMapping("/api/v1/qnas/{id}")
    public Long update(@PathVariable Long id, @RequestBody QnasUpdateRequestDto requestDto) {
        return qnasService.update(id, requestDto);
    }
}
