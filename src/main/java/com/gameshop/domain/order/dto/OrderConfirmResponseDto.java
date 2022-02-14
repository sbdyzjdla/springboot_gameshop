package com.gameshop.domain.order.dto;

import com.gameshop.domain.order.OrderStatus;
import com.gameshop.domain.order.delivery.DeliveryStatus;
import com.gameshop.domain.qnas.Qnas;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.text.html.parser.Entity;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class OrderConfirmResponseDto {

    //order
    private Long order_id;
    private Long user_id;
    private int order_price;          //총합
    private String order_title;
    private String order_name;
    private LocalDateTime modified_date;
    private String order_date;

    //delivery
    private DeliveryStatus deliveryStatus;
    private String address;
    private String detail_address;
    private String extra_address;
    private String postcode;
    private String delivery_status;
    private String phone_first;
    private String phone_second;
    private String phone_third;

    public static String enumToValue(DeliveryStatus deliveryStatus) {
        String status = "";
        if(deliveryStatus.toString() == "READY") {
            status = "배송준비";
        } else if(deliveryStatus.toString() == "TRANSIT") {
            status = "배송중";
        } else if(deliveryStatus.toString() == "COMP") {
            status = "배송완료";
        }
        return status;
    }
}
