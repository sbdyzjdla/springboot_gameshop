package com.gameshop.service;

import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.CartRepository;
import com.gameshop.domain.cart.dto.CartListResponseDto;
import com.gameshop.domain.cart.dto.CartSaveRequestDto;
import com.gameshop.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public Long save(CartSaveRequestDto requestDto) {
        return cartRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public List<CartListResponseDto> findAllDesc(SessionUser user) {

        return cartRepository.findAllDesc(user.getId()).stream()
                .map(CartListResponseDto::new)
                .collect(Collectors.toList());


    }
}
