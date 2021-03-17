package com.gameshop.web.dto;

import com.gameshop.domain.files.Files;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FilesSaveRequestDto {

    private String origin_filename;
    private String save_filename;
    private String owner;

    @Builder
    public FilesSaveRequestDto(String origin_filename, String save_filename, String owner) {
        this.origin_filename = origin_filename;
        this.save_filename = save_filename;
        this.owner = owner;
    }

    public Files toEntity() {
        return Files.builder()
                .origin_filename(origin_filename)
                .save_filename(save_filename)
                .owner(owner)
                .build();
    }

}
