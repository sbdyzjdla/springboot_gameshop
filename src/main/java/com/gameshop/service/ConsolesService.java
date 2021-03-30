package com.gameshop.service;

import com.gameshop.domain.consoles.ConsolesRepository;
import com.gameshop.domain.consoles.dto.ConsolesListResponseDto;
import com.gameshop.domain.consoles.dto.ConsolesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
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
        return consolesRepository.findAll().stream()
                .map(ConsolesListResponseDto::new)
                .collect(Collectors.toList());
    }

}