package com.gameshop.service;

import com.gameshop.domain.files.Files;
import com.gameshop.domain.files.FilesRepository;
import com.gameshop.web.dto.FilesResponseDto;
import com.gameshop.web.dto.FilesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        String filePath = baseDir + "\\" + save_filename;
        try {
            file.transferTo(new File(filePath));
            FilesSaveRequestDto requestDto = new FilesSaveRequestDto(origin_filename, save_filename, owner);
            return filesRepository.save(requestDto.toEntity()).getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public FilesResponseDto findById(Long id) {
        Files files = filesRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("이미지가 없습니다" + id));

        files.getOrigin_filename();
        String savePath = "C:\\쇼핑몰이미지\\" + files.getSave_filename();
        File file = new File("C:\\쇼핑몰이미지");
        System.out.println("파일리스트 : " + file.getName());
        //Path path = Paths.get(savePath);
        //Byte[] imgBytes = java.nio.file.Files.readAllBytes(path);
        InputStream in = getClass().getResourceAsStream(savePath);

        return new FilesResponseDto(files);
    }
}
