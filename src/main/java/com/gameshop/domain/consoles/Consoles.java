package com.gameshop.domain.consoles;

import com.gameshop.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Consoles extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String manufact;

    @Column
    private String edition;

    @Column
    private Long c_price;

    @Column
    private Long img_num;

    @Builder
    public Consoles(String manufact, String edition, Long c_price, Long img_num ) {
        this.manufact = manufact;
        this.edition = edition;
        this.c_price = c_price;
        this.img_num = img_num;
    }


}
