package com.gameshop.domain.order;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {

    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;

    protected Address() {}

    public Address(String postcode, String address, String detailAddress, String extraAddress) {
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;
    }
}
