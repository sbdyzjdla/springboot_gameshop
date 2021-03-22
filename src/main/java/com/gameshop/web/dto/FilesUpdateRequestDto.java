package com.gameshop.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FilesUpdateRequestDto {

    private String origin_filename;
    private String save_filename;

    @Builder
    public FilesUpdateRequestDto(String origin_filename, String save_filename) {

        this.origin_filename = origin_filename;
        this.save_filename = save_filename;
    }
}
