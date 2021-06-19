package com.gameshop.service;

import com.gameshop.domain.consoles.Consoles;
import com.gameshop.domain.consoles.dto.ConsolesResponseDto;
import com.gameshop.domain.products.Products;
import com.gameshop.domain.products.ProductsRepository;
import com.gameshop.domain.products.dto.ProductsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    @Transactional(readOnly = true)
    public Products findById(Long id) {
        Products entity = productsRepository.findById(id)
                .orElseThrow(() -> new
                        IllegalArgumentException("해당 상품이 없습니다"));

        return entity;
    }
}
