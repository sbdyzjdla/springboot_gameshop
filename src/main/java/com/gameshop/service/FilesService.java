package com.gameshop.service;

import com.gameshop.domain.files.Files;
import com.gameshop.domain.files.FilesRepository;
import com.gameshop.web.dto.FilesResponseDto;
import com.gameshop.web.dto.FilesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.h2.util.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilesService {

    private final FilesRepository filesRepository;

    //로컬
    private String baseDir = "/Users/yoonsung/web/save_img/";
    // 윈도우
    // private String baseDir = "C:\\쇼핑몰이미지\\";



    @Transactional
    public Long save(MultipartFile file, String owner) {

        UUID savename = UUID.randomUUID();

        String origin_filename = file.getOriginalFilename();
        String save_filename = savename.toString() + origin_filename.substring(origin_filename.lastIndexOf("."));

        String filePath = baseDir + save_filename;
        try {
            file.transferTo(new File(filePath));
            FilesSaveRequestDto requestDto = new FilesSaveRequestDto(origin_filename, save_filename, owner);
            return filesRepository.save(requestDto.toEntity()).getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public Long update(MultipartFile file, Long id) {

        UUID savename = UUID.randomUUID();

        String origin_filename = file.getOriginalFilename();
        String save_filename = savename.toString() + origin_filename.substring(origin_filename.lastIndexOf("."));

        String filePath = baseDir + save_filename;
        try {
            file.transferTo(new File(filePath));
            Files files = filesRepository.findById(id)
                    .orElseThrow(() -> new
                            IllegalArgumentException("해당 수정할 이미지가 없습니다 id " + id));

            files.update(origin_filename, save_filename);

            return id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }

    public ResponseEntity<Resource> findById(Long id) {
        Files files = filesRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("이미지가 없습니다" + id));

        files.getOrigin_filename();
        String savePath = baseDir + files.getSave_filename();

        Resource resource = new FileSystemResource(savePath);
        HttpHeaders header = new HttpHeaders();
        Path filePath = null;

        try {
            filePath = Paths.get(savePath);
            header.add("Content-Type", java.nio.file.Files.probeContentType(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
    }

    @Transactional
    public void delete(Long id) {

        Files files = filesRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("이미지가 없습니다" + id));

        filesRepository.delete(files);
    }
}
