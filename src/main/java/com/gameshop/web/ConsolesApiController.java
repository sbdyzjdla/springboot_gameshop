package com.gameshop.web;

import com.gameshop.domain.products.consoles.dto.ConsolesListResponseDto;
import com.gameshop.domain.products.consoles.dto.ConsolesSaveRequestDto;
import com.gameshop.domain.products.consoles.dto.ConsolesUpdateRequestDto;
import com.gameshop.service.ConsolesService;
import com.gameshop.service.FilesService;
import com.gameshop.domain.products.consoles.dto.ConsolesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ConsolesApiController - 콘솔기기(하드웨어) 컨트롤러
 */

@RequiredArgsConstructor
@RestController
public class ConsolesApiController {

    private final ConsolesService consolesService;
    private final FilesService filesService;

    /**
     * 관리자페이지 - 콘솔기기 저장
     * @param requestDto
     * @return
     */
    @PostMapping("/admin/consoles/save")
    public Long save(@ModelAttribute ConsolesSaveRequestDto requestDto) {
        if(requestDto.getConsoles_img() != null && !requestDto.getConsoles_img().isEmpty()) {
            Long file_id = filesService.save(requestDto.getConsoles_img(), "admin");
            requestDto.setImg_num(file_id);
        }
        return consolesService.save(requestDto);
    }

    /**
     * 관리자페이지 - 콘솔기기 리스트 조회
     * @return
     */
    @GetMapping("/admin/consoles/consoleList")
    public List<ConsolesListResponseDto> findAllDesc() { return consolesService.findAllDesc(); }

    /**
     * 관리자페이지 - 콘솔기기 상세조회
     * @param id
     * @return
     */
    @GetMapping("/admin/consoles/view/{id}")
    public ConsolesResponseDto findById(@PathVariable Long id) {
        return consolesService.findById(id);
    }

    /**
     * 관리자페이지 - 콘솔기기 수정
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/admin/consoles/update/{id}")
    public Long update(@PathVariable Long id, @ModelAttribute ConsolesUpdateRequestDto requestDto) {
        if(!requestDto.getConsoles_img().isEmpty()) {
            Long img_num = consolesService.findByImgNum(id);
            filesService.update(requestDto.getConsoles_img(), img_num);
        }

        return consolesService.update(id, requestDto);
    }

    /**
     * 상품_콘솔 - 닌텐도 기기 조회
     * @param p_num
     * @return
     */
    @GetMapping(value = {"/api/v1/consoles/nintendoCList/{p_num}", "/api/v1/consoles/nintendoCList/" } )
    public Page<ConsolesListResponseDto> findAllNint(@PathVariable(required = false) Integer p_num) {
        if(p_num == null) {
            p_num = 0;
        }
        return consolesService.findAllNint(p_num);
    }

    /**
     * 상품_콘솔 - 플스5 기기 조회
     * @param p_num
     * @return
     */
    @GetMapping(value = {"/api/v1/consoles/ps5CList/{p_num}", "/api/v1/consoles/ps5CList/"})
    public Page<ConsolesListResponseDto> findAllPs5(@PathVariable(required = false) Integer p_num) {
        if(p_num == null) {
            p_num = 0;
        }
        return consolesService.findAllPs5(p_num);
    }

}
