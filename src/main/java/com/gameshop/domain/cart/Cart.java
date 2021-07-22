package com.gameshop.domain.cart;

import com.gameshop.domain.BaseTimeEntity;
import com.gameshop.domain.products.Products;
import com.gameshop.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
@Getter
@Entity
public class Cart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long id;
    private Long user_id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartProducts> cartProducts = new ArrayList<CartProducts>();

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
