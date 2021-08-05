package com.gameshop.domain.order.delivery;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryResponseDto {

    private String postcode;
    private String address;
    private String detailAddress;
    private String extraAddress;

    private Long   order_id;
    private String order_name;
    private String phone_first;
    private String phone_second;
    private String phone_third;

    private String delivery_status;

    @Builder
    public DeliveryResponseDto(String postcode, String address, String detailAddress, String extraAddress,
            Long order_id, String order_name, String phone_first, String phone_second, String phone_third,
                               String delivery_status) {
        this.postcode = postcode;
        this.address = address;
        this.detailAddress = detailAddress;
        this.extraAddress = extraAddress;

        this.order_id = order_id;
        this.order_name = order_name;
        this.phone_first = phone_first;
        this.phone_second = phone_second;
        this.phone_third = phone_third;
        this.delivery_status = delivery_status;
    }
}
