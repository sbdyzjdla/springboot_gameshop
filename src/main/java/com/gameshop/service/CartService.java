package com.gameshop.service;

import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.*;
import com.gameshop.domain.cart.dto.CartListResponseDto;
import com.gameshop.domain.products.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartRepositorySupport cartRepositorySupport;
    private final CartProductsRepository cartProductsRepository;

    @Transactional
    public Long save(Products products, int quantity, Long userId) {

        CartProducts cartProducts = CartProducts.builder()
                .products(products)
                .p_price(products.getP_price())
                .orderPrice(products.getP_price()*quantity)
                .quantity(quantity)
                .build();
        cartProductsRepository.save(cartProducts);
        Cart cart = Cart.createCart(userId ,cartProducts);

        return cartRepository.save(cart).getId();
    }

    @Transactional
    public Long update(Long id, int quantity) {
        CartProducts cartProducts = cartProductsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        cartProducts.update(quantity);

        return id;
    }

    @Transactional
    public Long del(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        cartRepository.delete(cart);

        return id;
    }

    @Transactional
    public List<CartListResponseDto> findAllUser(SessionUser user) {
        return cartRepositorySupport.findAllUser(user.getId()).stream()
                .map(CartListResponseDto::new)
                .collect(Collectors.toList());

//        return cartRepositorySupport.findAllUser(user.getId());

    }

    @Transactional
    public Cart findById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        return cart;
    }

}
