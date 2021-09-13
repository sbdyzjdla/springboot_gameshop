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

@RequiredArgsConstructor
@RestController
public class ConsolesApiController {

    private final ConsolesService consolesService;
    private final FilesService filesService;

    @PostMapping("/admin/consoles/save")
    public Long save(@ModelAttribute ConsolesSaveRequestDto requestDto) {
        if(requestDto.getConsoles_img() != null && !requestDto.getConsoles_img().isEmpty()) {
            Long file_id = filesService.save(requestDto.getConsoles_img(), "admin");
            requestDto.setImg_num(file_id);
        }
        return consolesService.save(requestDto);
    }

    @GetMapping("/admin/consoles/consoleList")
    public List<ConsolesListResponseDto> findAllDesc() { return consolesService.findAllDesc(); }

    @GetMapping("/admin/consoles/view/{id}")
    public ConsolesResponseDto findById(@PathVariable Long id) {
        return consolesService.findById(id);
    }

    @PutMapping("/admin/consoles/update/{id}")
    public Long update(@PathVariable Long id, @ModelAttribute ConsolesUpdateRequestDto requestDto) {
        if(!requestDto.getConsoles_img().isEmpty()) {
            Long img_num = consolesService.findByImgNum(id);
            filesService.update(requestDto.getConsoles_img(), img_num);
        }

        return consolesService.update(id, requestDto);
    }

//    @GetMapping("/api/v1/consoles/nintendoCList")
//    public List<ConsolesListResponseDto> findAllNint() {
//        return consolesService.findAllNint();
//    }

    @GetMapping(value = {"/api/v1/consoles/nintendoCList/{p_num}", "/api/v1/consoles/nintendoCList/" } )
    public Page<ConsolesListResponseDto> findAllNint(@PathVariable(required = false) Integer p_num) {
        if(p_num == null) {
            p_num = 0;
        }
        return consolesService.findAllNint(p_num);
    }

    @GetMapping(value = {"/api/v1/consoles/ps5CList/{p_num}", "/api/v1/consoles/ps5CList/"})
    public Page<ConsolesListResponseDto> findAllPs5(@PathVariable(required = false) Integer p_num) {
        if(p_num == null) {
            p_num = 0;
        }
        return consolesService.findAllPs5(p_num);
    }

}
