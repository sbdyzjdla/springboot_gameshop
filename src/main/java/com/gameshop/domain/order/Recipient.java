package com.gameshop.domain.order;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Recipient {

    private String order_name;
    private String phone_first;
    private String phone_second;
    private String phone_third;

    protected Recipient() {}

    public Recipient(String order_name, String phone_first, String phone_second, String phone_third) {
        this.order_name = order_name;
        this.phone_first = phone_first;
        this.phone_second = phone_second;
        this.phone_third = phone_third;
    }
}
