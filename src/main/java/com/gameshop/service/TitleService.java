package com.gameshop.service;

import com.gameshop.domain.products.consoles.dto.ConsolesListResponseDto;
import com.gameshop.domain.products.titles.TitleRepositroy;
import com.gameshop.domain.products.titles.Titles;
import com.gameshop.domain.products.titles.TitlesRepositorySupport;
import com.gameshop.domain.products.titles.dto.TitlesListResponseDto;
import com.gameshop.domain.products.titles.dto.TitlesResponseDto;
import com.gameshop.domain.products.titles.dto.TitlesSaveRequestDto;
import com.gameshop.domain.products.titles.dto.TitlesUpdateRequestDto;
import com.querydsl.core.QueryResults;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TitleService {

    private final TitleRepositroy titleRepositroy;
    private final TitlesRepositorySupport titlesRepositorySupport;

    @Transactional
    public Long save(TitlesSaveRequestDto requestDto) {
        return titleRepositroy.save(requestDto.toEntity()).getId();
    }


    @Transactional(readOnly = true)
    public TitlesResponseDto findById(Long id) {
        Titles entity = titleRepositroy.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시물이 없습니다"));

        return new TitlesResponseDto(entity);
    }

    @Transactional
    public Long update(Long id, TitlesUpdateRequestDto requestDto) {
        Titles consoles = titleRepositroy.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        consoles.update(requestDto.getManufact(), requestDto.getP_name(), requestDto.getP_price(), requestDto.getQuantity());
        return id;
    }

    public Long findByImgNum(Long id) {
        Titles entity = titleRepositroy.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id" + id));
        return entity.getImg_num();
    }

    @Transactional
    public Page<TitlesListResponseDto> findAllNS(int p_num) {
        PageRequest paging = PageRequest.of(p_num, 9, Sort.by("id").descending());
        QueryResults<Titles> query = titlesRepositorySupport.findAllNS(paging);
        List<TitlesListResponseDto> resultList = query.getResults().stream()
                .map(TitlesListResponseDto::new)
                .collect(Collectors.toList());
        return new PageImpl<>(resultList, paging, query.getTotal());
    }

    @Transactional
    public Page<TitlesListResponseDto> findAllPS(int p_num) {
        PageRequest paging = PageRequest.of(p_num, 9, Sort.by("id").descending());
        QueryResults<Titles> query = titlesRepositorySupport.findAllPS(paging);
        List<TitlesListResponseDto> resultList = query.getResults().stream()
                .map(TitlesListResponseDto::new)
                .collect(Collectors.toList());
        return new PageImpl<>(resultList, paging, query.getTotal());
    }
}
