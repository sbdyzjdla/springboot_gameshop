package com.gameshop.service;

import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.CartProducts;
import com.gameshop.domain.cart.CartRepository;
import com.gameshop.domain.products.Products;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;

    @Transactional
    public Long save(Products products, int quantity, Long userId) {

        CartProducts cartProducts = CartProducts.builder()
                .products(products)
                .orderPrice(products.getP_price())
                .quantity(quantity)
                .build();

        Cart cart = Cart.createCart(userId ,cartProducts);

        return cartRepository.save(cart).getId();
    }
//
//    @Transactional
//    public List<CartListResponseDto> findAllDesc(SessionUser user) {
//
//        return cartRepository.findAllDesc(user.getId()).stream()
//                .map(CartListResponseDto::new)
//                .collect(Collectors.toList());
//
//
//    }
}
