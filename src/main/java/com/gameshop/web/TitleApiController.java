package com.gameshop.web;

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

@RequiredArgsConstructor
@RestController
public class TitleApiController {

    private final TitleService titleService;
    private final FilesService filesService;

    @PostMapping("/admin/titles/save")
    public Long save(@ModelAttribute TitlesSaveRequestDto requestDto) {
        if(requestDto.getProducts_img() != null && !requestDto.getProducts_img().isEmpty()) {
            Long file_id = filesService.save(requestDto.getProducts_img(), "admin");
            requestDto.setImg_num(file_id);
        }
        return titleService.save(requestDto);
    }
    @GetMapping("/admin/titles/view/{id}")
    public TitlesResponseDto findById(@PathVariable Long id) {
        return titleService.findById(id);
    }

    @PutMapping("/admin/titles/update/{id}")
    public Long update(@PathVariable Long id, @ModelAttribute TitlesUpdateRequestDto requestDto) {
        if(!requestDto.getProducts_img().isEmpty()) {
            Long img_num = titleService.findByImgNum(id);
            filesService.update(requestDto.getProducts_img(), img_num);
        }

        return titleService.update(id, requestDto);
    }

    @GetMapping(value = {"/api/v1/titles/nsSoftList/" , "/api/v1/titles/nsSoftList/{p_num}"})
    public Page<TitlesListResponseDto> findAllNint(@PathVariable(required = false) Integer p_num) {
        if(p_num == null) {
            p_num = 0;
        }
        return titleService.findAllNS(p_num);
    }

    @GetMapping(value = {"/api/v1/titles/ps5SoftList/", "/api/v1/titles/ps5SoftList/{p_num}"})
    public Page<TitlesListResponseDto> findAllPs5(@PathVariable(required = false) Integer p_num) {
        if(p_num == null) {
            p_num = 0;
        }
        return titleService.findAllPS(p_num);
    }

}
