package com.gameshop.domain.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderDetailResponseDto {

    private Long order_detail_id;
    private Long order_id;
    private Long product_id;
    private Long img_num;
    private String p_name;
    private int orderPrice;
    private int quantity;


}
