package com.gameshop.service;

import com.gameshop.domain.qnas.Qnas;
import com.gameshop.domain.qnas.QnasRepository;
import com.gameshop.web.dto.QnasListResponseDto;
import com.gameshop.web.dto.QnasResponseDto;
import com.gameshop.web.dto.QnasSaveRequestDto;
import com.gameshop.web.dto.QnasUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class QnasService {
    private final QnasRepository qnasRepository;

    @Transactional
    public Long save(QnasSaveRequestDto requestDto) {
        return qnasRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, QnasUpdateRequestDto requestDto) {

        Qnas qnas = qnasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        qnas.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public QnasResponseDto findById(Long id) {
        Qnas entity = qnasRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id" + id));

        return new QnasResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<QnasListResponseDto> findAllDesc() {
        return qnasRepository.findAll().stream()
                .map(QnasListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Qnas qnas = qnasRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id" + id));

        qnasRepository.delete(qnas);
    }

    public Long findByImgNum(Long id) {
        Qnas entity = qnasRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id" + id));
        return entity.getImg_num();
    }
}
