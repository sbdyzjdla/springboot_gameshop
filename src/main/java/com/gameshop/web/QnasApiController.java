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
    public Long save(@ModelAttribute("requestDto") QnasSaveRequestDto requestDto) {
        if(!requestDto.getQnas_img().isEmpty()) {
            Long file_id = filesService.save(requestDto.getQnas_img(), requestDto.getAuthor());
            requestDto.setImg_num(file_id);
        }
        return qnasService.save(requestDto);
    }

    @PutMapping("/api/v1/qnas/{id}")
    public Long update(@PathVariable Long id, @ModelAttribute QnasUpdateRequestDto requestDto) {
        if(!requestDto.getQnas_img().isEmpty()) {
            Long img_num = qnasService.findByImgNum(id);
            filesService.update(requestDto.getQnas_img(), img_num);
        }

        return qnasService.update(id, requestDto);
    }
    @GetMapping("/api/v1/qnas/{id}")
    public QnasResponseDto findById (@PathVariable Long id) {
        return  qnasService.findById(id);
    }

    @GetMapping("/api/v1/qnas/qnaslist")
    public List<QnasListResponseDto> findAll() {
        return qnasService.findAllDesc();
    }

    @DeleteMapping("/api/v1/qnas/{id}")
    public Long delete(@PathVariable Long id) {
        Long img_num = qnasService.findByImgNum(id);
        qnasService.delete(id);
        filesService.delete(img_num);
        return id;
    }

}
