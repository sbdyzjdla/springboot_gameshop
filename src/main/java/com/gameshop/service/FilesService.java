package com.gameshop.service;

import com.gameshop.domain.files.FilesRepository;
import com.gameshop.web.dto.FilesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilesService {

    private final FilesRepository filesRepository;

    @Transactional
    public Long save(MultipartFile file, String owner) {

        UUID savename = UUID.randomUUID();

        String origin_filename = file.getOriginalFilename();
        String save_filename = savename.toString();

        String baseDir = "C:\\쇼핑몰이미지";
        String filePath = baseDir + "\\" + origin_filename;

        try {
            file.transferTo(new File(filePath));
            FilesSaveRequestDto requestDto = new FilesSaveRequestDto(origin_filename, save_filename, owner);
            return filesRepository.save(requestDto.toEntity()).getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
