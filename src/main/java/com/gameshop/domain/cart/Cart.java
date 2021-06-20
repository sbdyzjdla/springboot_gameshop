package com.gameshop.domain.cart;

import com.gameshop.domain.products.Products;
import com.gameshop.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long id;

    @Column
    private Long user_id;

    @OneToMany(mappedBy = "cart")
    private List<CartProducts> cartProducts = new ArrayList<CartProducts>();

    private int quantity;

//    @Builder
//    public Cart (Long user_id, int quantity, CartProducts... cartProducts){
//        this.user_id = user_id;
//        this.quantity = quantity;
//        for(CartProducts cartProduct : cartProducts) {
//            this.cartProducts.add(cartProduct);
//        }
//    }

    public static Cart createCart(Long user_id, CartProducts... cartProducts) {
        Cart cart = new Cart();
        cart.setUser_id(user_id);
        for(CartProducts cartProduct : cartProducts) {
            cart.cartProducts.add(cartProduct);
        }
        return cart;
    }

}
