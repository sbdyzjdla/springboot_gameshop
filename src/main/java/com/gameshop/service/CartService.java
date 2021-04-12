package com.gameshop.service;

import com.gameshop.domain.cart.CartRepository;
import com.gameshop.domain.cart.dto.CartSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    public Long save(CartSaveRequestDto requestDto) {
        return cartRepository.save(requestDto.toEntity()).getId();
    }
}
