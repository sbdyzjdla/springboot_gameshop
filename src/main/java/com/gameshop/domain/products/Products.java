package com.gameshop.domain.products;

import com.gameshop.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public abstract class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String manufact;

    @Column
    private int p_price;

    @Column
    private int quiantity;

    @Column
    private String company;

    @Column
    private Long img_num;

    @Column
    private String category;


}
