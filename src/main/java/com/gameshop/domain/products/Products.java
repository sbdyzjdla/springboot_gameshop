package com.gameshop.domain.products;

import com.gameshop.domain.BaseTimeEntity;
import com.gameshop.domain.cart.Cart;
import com.gameshop.domain.cart.CartProducts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class Products extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCTS_ID")
    private Long id;

    @Column
    private String manufact;

    @Column
    private int p_price;

    @Column
    private String p_name;

    @Column
    private int quantity;

    @Column
    private Long img_num;

    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<CartProducts> cartProducts;

    public void removeStock(int quantity) {
        int restStock = this.quantity - quantity;
        if(restStock <0 ) {
            throw new RuntimeException("재고가 부족합니다");
        }
        this.quantity = restStock;
    }
}