package com.gameshop.domain.order.dto;

import com.gameshop.domain.order.Order;
import com.gameshop.domain.order.OrderDetail;
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

    private Long id;
    private String order_status;
    private Long order_img;
    private String order_title;
    private int order_price;
    private String modified_date;

    public OrderConfirmListResponse(Order entity) {
        this.id = entity.getId();
        this.order_status = enumToValue(entity.getOrderStatus());
        this.order_img = entity.getOrder_img();
        this.order_title = entity.getOrder_title();
        this.order_price = entity.getOrder_price();
        this.modified_date = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String enumToValue(OrderStatus orderStatus) {
        String status = "";
        if(orderStatus.toString() == "ORDER") {
            status = "주문";
        } else if(orderStatus.toString() == "READY") {
            status = "준비";
        } else if(orderStatus.toString() == "CANCEL") {
            status = "취소";
        }
        return status;
    }
}