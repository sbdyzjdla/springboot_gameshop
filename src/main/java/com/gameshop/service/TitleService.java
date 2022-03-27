package com.gameshop.service;

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

/**
 * TitleService - 게임타이틀(소프트웨어) 관리 서비스
 */

@RequiredArgsConstructor
@Service
public class TitleService {

    private final TitleRepositroy titleRepositroy;
    private final TitlesRepositorySupport titlesRepositorySupport;


    /**
     * 상품_타이틀 - 저장
     * @param requestDto
     * @return
     */
    @Transactional
    public Long save(TitlesSaveRequestDto requestDto) {
        return titleRepositroy.save(requestDto.toEntity()).getId();
    }

    /**
     * 상품_타이틀 - 조회
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public TitlesResponseDto findById(Long id) {
        Titles entity = titleRepositroy.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시물이 없습니다"));
        return new TitlesResponseDto(entity);
    }

    /**
     * 상품_타이틀 - 수정
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public Long update(Long id, TitlesUpdateRequestDto requestDto) {
        Titles consoles = titleRepositroy.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        consoles.update(requestDto.getConsole(), requestDto.getManufact(), requestDto.getP_name(), requestDto.getP_price(), requestDto.getQuantity());
        return id;
    }

    /**
     * 상품_타이틀 - 타이틀 이미지 id값 조회
     * @param id
     * @return
     */
    public Long findByImgNum(Long id) {
        Titles entity = titleRepositroy.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id" + id));
        return entity.getImg_num();
    }

    /**
     * 상품_타이틀 - 타이틀 내림차순 조회
     * @return
     */
    @Transactional
    public List<TitlesListResponseDto> findAllDesc() {
        return titleRepositroy.findAllDesc().stream()
                .map(TitlesListResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 상품_타이틀 - 닌텐도 타이틀 조회
     * @param p_num
     * @return
     */
    @Transactional
    public Page<TitlesListResponseDto> findAllNS(int p_num) {
        PageRequest paging = PageRequest.of(p_num, 9, Sort.by("id").descending());
        QueryResults<Titles> query = titlesRepositorySupport.findAllNS(paging);
        List<TitlesListResponseDto> resultList = query.getResults().stream()
                .map(TitlesListResponseDto::new)
                .collect(Collectors.toList());
        return new PageImpl<>(resultList, paging, query.getTotal());
    }

    /**
     * 상품_타이틀 - 플스 타이틀 조회
     * @param p_num
     * @return
     */
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
