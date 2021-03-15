package com.gameshop.domain.products;

import com.gameshop.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Products extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String p_name;

    @Column
    private Long p_amount;

    @Column
    private Long p_price;

    @Column
    private String p_img;

    @Column
    private String category;

    @Column
    private String company;

}
