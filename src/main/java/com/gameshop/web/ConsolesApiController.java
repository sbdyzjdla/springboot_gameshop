package com.gameshop.web;

import com.gameshop.domain.consoles.dto.ConsolesSaveRequestDto;
import com.gameshop.service.ConsolesService;
import com.gameshop.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ConsolesApiController {

    private final ConsolesService consolesService;
    private final FilesService filesService;

    @PostMapping("/admin/consoles/save")
    public Long save(@ModelAttribute ConsolesSaveRequestDto requestDto) {
        if(!requestDto.getQnas_img().isEmpty()) {
            Long file_id = filesService.save(requestDto.getQnas_img(), "admin");
            requestDto.setImg_num(file_id);
        }
        return consolesService.save(requestDto);
    }
}
