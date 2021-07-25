package com.gameshop.domain.order;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Recipient {

    private String order_name;
    private int phone_first;
    private int phone_second;
    private int phone_third;

    protected Recipient() {}

    public Recipient(String order_name, int phone_first, int phone_second, int phone_third) {
        this.order_name = order_name;
        this.phone_first = phone_first;
        this.phone_second = phone_second;
        this.phone_third = phone_third;
    }
}
