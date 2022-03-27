package com.gameshop.web;

import com.gameshop.domain.qnas.Qnas;
import com.gameshop.service.FilesService;
import com.gameshop.service.QnasService;
import com.gameshop.domain.qnas.dto.QnasListResponseDto;
import com.gameshop.domain.qnas.dto.QnasResponseDto;
import com.gameshop.domain.qnas.dto.QnasSaveRequestDto;
import com.gameshop.domain.qnas.dto.QnasUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * QnasApiController - Q&A 컨트롤러
 */

@RequiredArgsConstructor
@RestController
public class QnasApiController {

    private final QnasService qnasService;
    private final FilesService filesService;

    /**
     * Q&A - Q&A 등록
     * @param requestDto
     * @return
     */
    @PostMapping("/api/v1/qnas")
    public Long save(@ModelAttribute QnasSaveRequestDto requestDto) {
        if(!requestDto.getQnas_img().isEmpty()) {
            Long file_id = filesService.save(requestDto.getQnas_img(), requestDto.getAuthor());
            requestDto.setImg_num(file_id);
        }
        return qnasService.save(requestDto);
    }

    /**
     * Q&A - Q&A 수정
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/api/v1/qnas/{id}")
    public Long update(@PathVariable Long id, @ModelAttribute QnasUpdateRequestDto requestDto) {
        if(!requestDto.getQnas_img().isEmpty()) {
            Long img_num = qnasService.findByImgNum(id);
            filesService.update(requestDto.getQnas_img(), img_num);
        }

        return qnasService.update(id, requestDto);
    }

    /**
     * Q&A - Q&A 상세조회
     * @param id
     * @return
     */
    @GetMapping("/api/v1/qnas/{id}")
    public QnasResponseDto findById (@PathVariable Long id) {
        return  qnasService.findById(id);
    }

    /**
     * Q&A - Q&A 리스트 조회
     * @return
     */
    @GetMapping("/api/v1/qnaslist")
    public List<QnasListResponseDto> findAll() {
        return qnasService.findAllDesc();
    }

    /**
     * Q&A - Q&A 리스트 조회 (페이징)
     * @param p_num
     * @return
     */
    @GetMapping("/api/v1/qnalist/{p_num}")
    public Page<Qnas> findAllPageDesc(@PathVariable int p_num) {
        return qnasService.findAllPageDesc(p_num);
    }

    /**
     * Q&A - Q&A 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/api/v1/qnas/{id}")
    public Long delete(@PathVariable Long id) {
        Long img_num = qnasService.findByImgNum(id);
        qnasService.delete(id);
        filesService.delete(img_num);
        return id;
    }

}
