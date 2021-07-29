package com.gameshop.domain.order.delivery;

import com.gameshop.domain.order.Address;
import com.gameshop.domain.order.Order;
import com.gameshop.domain.order.Recipient;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Delivery {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Embedded
    private Recipient recipient;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Builder
    public Delivery(Order order, Address address, Recipient recipient, DeliveryStatus deliveryStatus) {
        this.order = order;
        this.address = address;
        this.recipient = recipient;
        this.deliveryStatus = deliveryStatus;
    }
}
