package com.gameshop.service;

import com.gameshop.domain.products.Products;
import com.gameshop.domain.products.ProductsRepository;
import com.gameshop.domain.products.dto.ProductsOrderResponseDto;
import com.gameshop.domain.products.dto.ProductsResponseDto;
import com.gameshop.domain.products.titles.Titles;
import com.gameshop.domain.products.titles.dto.TitlesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProductsService - 상품 서비스
 */

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    /**
     * 상품 - 상품 상세조회 (dto 반환)
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public ProductsResponseDto findById(Long id) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다"));

        return new ProductsResponseDto(entity);
    }

    /**
     * 상품 - 상품 상세조회 (entity 반환)
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public Products findByIdCart(Long id) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다"));
        return entity;
    }

    /**
     * 상품
     * @param id
     * @param quantity
     * @return
     */
    @Transactional(readOnly = true)
    public ProductsOrderResponseDto findByIdOrder(Long id, int quantity) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다"));
        entity.setQuantity(quantity);
        return new ProductsOrderResponseDto(entity);
    }

    /**
     * 상품 - 상품 수량 변경
     * @param id
     * @param quantity
     */
    @Transactional
    public void updateQuantity(Long id, int quantity) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다"));
        entity.removeStock(quantity);
    }

    /**
     * 상품 - 상품 삭제
     * @param id
     * @return
     */
    @Transactional
    public Long products_del(Long id) {
        productsRepository.deleteById(id);
        return id;
    }

}
