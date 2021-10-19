package com.gameshop.domain.order.dto;

import com.gameshop.domain.order.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@NoArgsConstructor
public class OrderConfirmListResponse {

    private Long order_id;
    private OrderStatus order_status;
    private int order_price;
    private int p_price;
    private int quantity;
    private Long img_num;
    private String p_name;
    private LocalDateTime date_time;
//    private String date = date_time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    //String formatDate = findAll.get(0).getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
}
