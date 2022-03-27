package com.gameshop.service;

import com.gameshop.domain.qnas.Qnas;
import com.gameshop.domain.qnas.QnasRepository;
import com.gameshop.domain.qnas.QnasRepositorySupport;
import com.gameshop.domain.qnas.dto.QnasListResponseDto;
import com.gameshop.domain.qnas.dto.QnasResponseDto;
import com.gameshop.domain.qnas.dto.QnasSaveRequestDto;
import com.gameshop.domain.qnas.dto.QnasUpdateRequestDto;
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
 * QnasService - Q&A 서비스
 */

@RequiredArgsConstructor
@Service
public class QnasService {

    private final QnasRepository qnasRepository;
    private final QnasRepositorySupport qnasRepositorySupport;

    /**
     * Q&A - Q&A 저장
     * @param requestDto
     * @return
     */
    @Transactional
    public Long save(QnasSaveRequestDto requestDto) {
        return qnasRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * Q&A - Q&A 수정
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public Long update(Long id, QnasUpdateRequestDto requestDto) {

        Qnas qnas = qnasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id=" + id));

        qnas.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    /**
     * Q&A - Q&A 상세조회
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public QnasResponseDto findById(Long id) {
        Qnas entity = qnasRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id" + id));

        return new QnasResponseDto(entity);
    }

    /**
     * Q&A - Q&A 내림차순 조회
     * @return
     */
    @Transactional(readOnly = true)
    public List<QnasListResponseDto> findAllDesc() {
        return qnasRepository.findAll().stream()
                .map(QnasListResponseDto::new)
                .collect(Collectors.toList());
    }

    /**
     * Q&A - Q&A 페이징 내림차순 조회
     * @param p_num
     * @return
     */
    @Transactional(readOnly = true)
    public Page<Qnas> findAllPageDesc(int p_num) {
        PageRequest paging = PageRequest.of(p_num-1, 5, Sort.by("id").descending());
        return qnasRepository.findAll(paging);
    }

    /**
     * Q&A - Q&A 삭제
     * @param id
     */
    @Transactional
    public void delete (Long id) {
        Qnas qnas = qnasRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id" + id));
        qnasRepository.delete(qnas);
    }

    /**
     * Q&A - Q&A 이미지 id값 조회
     * @param id
     * @return
     */
    @Transactional
    public Long findByImgNum(Long id) {
        Qnas entity = qnasRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 게시글이 없습니다. id" + id));
        return entity.getImg_num();
    }

    /**
     * Q&A - Q&A 제목검색 결과 조회
     * @param search
     * @param p_num
     * @return
     */
    @Transactional
    public Page<Qnas> findByTitle(String search, int p_num) {
        PageRequest paging = PageRequest.of(p_num-1, 5, Sort.by("id").descending());
        return new PageImpl<>(qnasRepositorySupport.findByTitle(search, paging));
    }

}
