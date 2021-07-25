package com.gameshop.domain.order.delivery;

import com.gameshop.domain.order.Address;
import com.gameshop.domain.order.Order;
import com.gameshop.domain.order.Recipient;

import javax.persistence.*;

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

}
