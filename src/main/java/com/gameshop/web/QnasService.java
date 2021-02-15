package com.gameshop.web;

import com.gameshop.domain.qnas.QnasRepository;
import com.gameshop.web.dto.QnasSaveRequestDto;
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
}
