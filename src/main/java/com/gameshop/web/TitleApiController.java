package com.gameshop.web;

import com.gameshop.domain.products.consoles.dto.ConsolesListResponseDto;
import com.gameshop.domain.products.titles.dto.TitlesListResponseDto;
import com.gameshop.domain.products.titles.dto.TitlesResponseDto;
import com.gameshop.domain.products.titles.dto.TitlesSaveRequestDto;
import com.gameshop.domain.products.titles.dto.TitlesUpdateRequestDto;
import com.gameshop.service.FilesService;
import com.gameshop.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TitleApiController - 게임타이틀(소프트웨어) 관리 컨트롤러
 */

@RequiredArgsConstructor
@RestController
public class TitleApiController {

    private final TitleService titleService;
    private final FilesService filesService;

    /**
     * 관리자페이지 - 타이틀 저장
     * @param requestDto
     * @return
     */
    @PostMapping("/admin/titles/save")
    public Long save(@ModelAttribute TitlesSaveRequestDto requestDto) {
        if(requestDto.getProducts_img() != null && !requestDto.getProducts_img().isEmpty()) {
            Long file_id = filesService.save(requestDto.getProducts_img(), "admin");
            requestDto.setImg_num(file_id);
        }
        return titleService.save(requestDto);
    }

    /**
     * 관리자페이지 - 타이틀 상세보기
     * @param id
     * @return
     */
    @GetMapping("/admin/titles/view/{id}")
    public TitlesResponseDto findById(@PathVariable Long id) {
        return titleService.findById(id);
    }

    /**
     * 관리자페이지 - 타이틀 수정
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/admin/titles/update/{id}")
    public Long update(@PathVariable Long id, @ModelAttribute TitlesUpdateRequestDto requestDto) {
        if(!requestDto.getProducts_img().isEmpty()) {
            Long img_num = titleService.findByImgNum(id);
            filesService.update(requestDto.getProducts_img(), img_num);
        }

        return titleService.update(id, requestDto);
    }

    /**
     * 관리자페이지 - 타이틀 리스트 조회
     * @return
     */
    @GetMapping("/admin/titles/titleList")
    public List<TitlesListResponseDto> findAllDesc() { return titleService.findAllDesc(); }

    /**
     * 상품_타이틀 - 닌텐도 타이틀 조회
     * @param p_num
     * @return
     */
    @GetMapping(value = {"/api/v1/titles/nsSoftList/" , "/api/v1/titles/nsSoftList/{p_num}"})
    public Page<TitlesListResponseDto> findAllNint(@PathVariable(required = false) Integer p_num) {
        if(p_num == null) {
            p_num = 0;
        }
        return titleService.findAllNS(p_num);
    }

    /**
     * 상품_타이틀 - 플스5 타이틀 조회
     * @param p_num
     * @return
     */
    @GetMapping(value = {"/api/v1/titles/ps5SoftList/", "/api/v1/titles/ps5SoftList/{p_num}"})
    public Page<TitlesListResponseDto> findAllPs5(@PathVariable(required = false) Integer p_num) {
        if(p_num == null) {
            p_num = 0;
        }
        return titleService.findAllPS(p_num);
    }

}
