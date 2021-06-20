package com.gameshop.domain.cart;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CartProductsRepository extends JpaRepository<CartProducts, Long> {

}
