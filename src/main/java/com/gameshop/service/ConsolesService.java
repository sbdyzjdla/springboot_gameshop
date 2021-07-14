package com.gameshop.service;

import com.gameshop.domain.products.consoles.Consoles;
import com.gameshop.domain.products.consoles.ConsolesRepository;
import com.gameshop.domain.products.consoles.dto.ConsolesListResponseDto;
import com.gameshop.domain.products.consoles.dto.ConsolesResponseDto;
import com.gameshop.domain.products.consoles.dto.ConsolesSaveRequestDto;
import com.gameshop.domain.products.consoles.dto.ConsolesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ConsolesService {

    private final ConsolesRepository consolesRepository;

    @Transactional
    public Long save(ConsolesSaveRequestDto requestDto) {
        return consolesRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<ConsolesListResponseDto> findAllDesc() {
        return consolesRepository.findAllDesc().stream()
                .map(ConsolesListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConsolesResponseDto findById(Long id) {
        Consoles entity = consolesRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시물이 없습니다"));

        return new ConsolesResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, ConsolesUpdateRequestDto requestDto) {
        Consoles consoles = consolesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        consoles.update(requestDto.getManufact(), requestDto.getP_name(), requestDto.getP_price(), requestDto.getQuantity());
        return id;
    }

    public Long findByImgNum(Long id) {
        Consoles entity = consolesRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id" + id));
        return entity.getImg_num();
    }

    @Transactional
    public List<ConsolesListResponseDto> findAllNint() {
        return consolesRepository.findAllNint().stream()
                .map(ConsolesListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<ConsolesListResponseDto> findAllPs5() {
        return consolesRepository.findAllPs5().stream()
                .map(ConsolesListResponseDto::new)
                .collect(Collectors.toList());
    }


}