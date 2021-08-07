package com.gameshop.service;

import com.gameshop.config.auth.dto.SessionUser;
import com.gameshop.domain.cart.*;
import com.gameshop.domain.cart.dto.CartListResponseDto;
import com.gameshop.domain.cart.dto.CartProdListResDto;
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
    public Long add(Products products, int quantity, Long userId) {

        CartProducts cartProducts = CartProducts.builder()
                .products(products)
                .p_price(products.getP_price())
                .orderPrice(products.getP_price()*quantity)
                .quantity(quantity)
                .build();
        cartProductsRepository.save(cartProducts);

        Long cartCount = cartRepositorySupport.findByCart(userId);
        if(cartCount.equals(0L)) {
            Cart new_cart = Cart.createCart(userId ,cartProducts);
            return cartRepository.save(new_cart).getId();
        } else {
            Cart cart = cartRepositorySupport.findByUserId(userId)
                    .orElseThrow(() -> new IllegalArgumentException("cart not exist"));
            cart.addCartProducts(cartProducts);
            return cart.getId();
        }
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
    public Long cartProductsDel(Long id) {
        CartProducts cartProducts = cartProductsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());

        cartProductsRepository.delete(cartProducts);
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
    public List<CartProdListResDto> findAllCartUser(SessionUser user) {
        return cartRepositorySupport.findAllCartUser(user.getId());

//        return cartRepositorySupport.findAllUser(user.getId());

    }

    @Transactional
    public Cart findById(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        return cart;
    }

    @Transactional
    public Cart findByUserId(Long user_id) {
        Cart cart = cartRepositorySupport.findByUserId(user_id)
                .orElseThrow(() -> new IllegalArgumentException());
        return cart;
    }

    @Transactional
    public CartProdListResDto CartProdFindById(Long id) {
        return cartRepositorySupport.CartProdFindById(id);
    }

    @Transactional
    public CartProducts cartProdEntityFindById(Long id) {
        return cartRepositorySupport.cartProdEntityFindById(id);
    }

    @Transactional
    public List<CartProducts> findByOrderId(Long order_id) {
        return cartRepositorySupport.findByOrderId(order_id);
    }
}
