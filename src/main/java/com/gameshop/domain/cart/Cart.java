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

    public void addCartProducts(CartProducts paramCartProd) {
        cartProducts.add(paramCartProd);
        paramCartProd.setCart(this);
    }

    public static Cart createCart(Long user_id, CartProducts... cartProducts) {
        Cart cart = new Cart();
        cart.setUser_id(user_id);
        for(CartProducts cartProduct : cartProducts) {
            cart.addCartProducts(cartProduct);
        }
        return cart;
    }

}
