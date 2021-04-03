package com.gameshop.web;

import com.gameshop.domain.consoles.dto.ConsolesListResponseDto;
import com.gameshop.domain.consoles.dto.ConsolesSaveRequestDto;
import com.gameshop.service.ConsolesService;
import com.gameshop.service.FilesService;
import com.gameshop.domain.consoles.dto.ConsolesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ConsolesApiController {

    private final ConsolesService consolesService;
    private final FilesService filesService;

    @PostMapping("/admin/consoles/save")
    public Long save(@ModelAttribute ConsolesSaveRequestDto requestDto) {
        if(!requestDto.getConsoles_img().isEmpty()) {
            Long file_id = filesService.save(requestDto.getConsoles_img(), "admin");
            requestDto.setImg_num(file_id);
        }
        return consolesService.save(requestDto);
    }

    @GetMapping("/admin/consoles/consoleList")
    public List<ConsolesListResponseDto> findAll() { return consolesService.findAllDesc(); }

    @GetMapping("/admin/consoles/view/{id}")
    public ConsolesResponseDto findById(@PathVariable Long id) {
        return consolesService.findById(id);
    }
}
