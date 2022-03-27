package com.gameshop.domain.files.dto;

import com.gameshop.domain.files.Files;
import lombok.Getter;

@Getter
public class FilesResponseDto {

    private String origin_filename;
    private String save_filename;

    public FilesResponseDto(Files entity) {
        this.origin_filename = entity.getOrigin_filename();
        this.save_filename = entity.getSave_filename();
    }
}
