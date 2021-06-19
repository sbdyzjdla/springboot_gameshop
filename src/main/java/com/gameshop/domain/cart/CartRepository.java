package com.gameshop.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

//    @Query("SELECT c FROM Cart c WHERE c.user_id = ?1 ORDER BY c.id DESC")
//    List<Cart> findAllDesc(Long user_id);

//    @Query("SELECT cart.id, cart.user_id, cart.product_id, cart.quantity, cart.img_num, cart.p_name " +
//            "FROM Cart as cart right outer join Products as products " +
//            "on cart.product_id = products.id and cart.user_id = :paramUser" +
//            " ORDER BY cart.id DESC")
//    List<Cart> findAllDesc(@Param("paramUser") Long user_id);
}
