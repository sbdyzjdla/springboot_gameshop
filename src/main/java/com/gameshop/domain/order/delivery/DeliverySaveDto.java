package com.gameshop.domain.order.delivery;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeliverySaveDto {

    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;

    private String order_name;
    private String phone_first;
    private String phone_second;
    private String phone_third;

    @Builder
    public DeliverySaveDto(String postcode, String address, String detailAddress, String extraAddress,
                String order_name, String phone_first, String phone_second, String phone_third) {
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;

        this.order_name = order_name;
        this.phone_first = phone_first;
        this.phone_second = phone_second;
        this.phone_third = phone_third;
    }
}
