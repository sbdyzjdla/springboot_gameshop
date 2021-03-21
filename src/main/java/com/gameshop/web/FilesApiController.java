package com.gameshop.web;

import com.gameshop.service.FilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FilesApiController {

    private final FilesService filesService;

    @GetMapping("/display/{id}")
    public ResponseEntity<Resource> display(@PathVariable Long id) {

        return filesService.findById(id);

    }
}
