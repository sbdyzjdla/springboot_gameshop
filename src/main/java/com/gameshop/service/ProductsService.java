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

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Transactional(readOnly = true)
    public ProductsResponseDto findById(Long id) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다"));

        return new ProductsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public Products findByIdCart(Long id) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다"));

        return entity;
    }

    @Transactional(readOnly = true)
    public ProductsOrderResponseDto findByIdOrder(Long id, int quantity) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다"));
        entity.setQuantity(quantity);
        return new ProductsOrderResponseDto(entity);
    }

    @Transactional
    public void updateQuantity(Long id, int quantity) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다"));
        entity.removeStock(quantity);
    }

}
