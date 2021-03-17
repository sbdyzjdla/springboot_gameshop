package com.gameshop.web;

import com.gameshop.service.FilesService;
import com.gameshop.service.QnasService;
import com.gameshop.web.dto.QnasListResponseDto;
import com.gameshop.web.dto.QnasResponseDto;
import com.gameshop.web.dto.QnasSaveRequestDto;
import com.gameshop.web.dto.QnasUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class QnasApiController {

    private final QnasService qnasService;
    private final FilesService filesService;

    @PostMapping("/api/v1/qnas")
    public Long save(@ModelAttribute QnasSaveRequestDto requestDto) {
        System.out.println("이미지!!!" + requestDto.getQnas_img().getOriginalFilename());
        filesService.save(requestDto.getQnas_img(), requestDto.getAuthor());
        return qnasService.save(requestDto);
    }

    @PutMapping("/api/v1/qnas/{id}")
    public Long update(@PathVariable Long id, @RequestBody QnasUpdateRequestDto requestDto) {
        return qnasService.update(id, requestDto);
    }
    @GetMapping("/api/v1/qnas/{id}")
    public QnasResponseDto findById (@PathVariable Long id) {
        return qnasService.findById(id);
    }

    @GetMapping("/api/v1/qnas/qnaslist")
    public List<QnasListResponseDto> findAll() {
        return qnasService.findAllDesc();
    }

    @DeleteMapping("/api/v1/qnas/{id}")
    public Long delete(@PathVariable Long id) {

        qnasService.delete(id);
        return id;
    }

}
