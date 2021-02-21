package com.gameshop.service;

import com.gameshop.domain.qnas.Qnas;
import com.gameshop.domain.qnas.QnasRepository;
import com.gameshop.web.dto.QnasResponseDto;
import com.gameshop.web.dto.QnasSaveRequestDto;
import com.gameshop.web.dto.QnasUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

}
