package com.gameshop.domain.order.dto;

import com.gameshop.domain.qnas.Qnas;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OrderConfirmResponseDto {

    private Long order_id;
    private Long user_id;
    private int order_price;
    private String address;
    private String detail_address;
    private String extra_address;
    private String postcode;
    private String delivery_status;
    private String order_name;
    private Long img_num;
    private String phone_first;
    private String phone_second;
    private String phone_third;

}
