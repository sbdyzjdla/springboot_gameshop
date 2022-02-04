package com.gameshop.domain.order;

import com.gameshop.domain.order.dto.OrderConfirmListResponse;
import com.gameshop.domain.order.dto.OrderConfirmResponseDto;
import com.gameshop.domain.order.dto.OrderListResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gameshop.domain.order.QOrder.order;
import static com.gameshop.domain.order.delivery.QDelivery.delivery;
import static com.gameshop.domain.cart.QCartProducts.cartProducts;
import static com.gameshop.domain.products.QProducts.products;
import static com.gameshop.domain.order.QOrderDetail.orderDetail;

@Repository
public class OrderRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public OrderRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Order.class);
        this.queryFactory = jpaQueryFactory;
    }

    public void del_order_ready(Long user_id) {
         queryFactory.delete(order)
                .where(order.user_id.eq(user_id))
                .execute();
    }

    public Long find_order_ready_cnt(Long user_id) {
        return queryFactory.selectFrom(order)
                .where(order.user_id.eq(user_id)
                .and(order.orderStatus.eq(OrderStatus.READY)))
                .fetchCount();
    }

    public Order find_order_ready(Long user_id) {
        return queryFactory.selectFrom(order)
                .where(order.user_id.eq(user_id)
                        .and(order.orderStatus.eq(OrderStatus.READY)))
                .fetchOne();
    }

    public List<Order> find_order_ready_total_price(Long user_id) {
        return queryFactory.selectFrom(order)
                .where(order.user_id.eq(user_id)
                    .and(order.orderStatus.eq(OrderStatus.READY))).fetch();
    }
    public OrderConfirmResponseDto order_confirm(Long id) {
        return queryFactory.select(Projections.fields(OrderConfirmResponseDto.class,
                order.id.as("order_id"),
                order.order_price.as("order_price"),
                order.user_id.as("user_id"),
                delivery.address.address.as("address"),
                delivery.address.detailAddress.as("detail_address"),
                delivery.address.extraAddress.as("extra_address"),
                delivery.address.postcode.as("postcode"),
                delivery.deliveryStatus.as("delivery_status"),
                delivery.recipient.order_name.as("order_name"),
                delivery.recipient.phone_first.as("phone_first"),
                delivery.recipient.phone_second.as("phone_second"),
                delivery.recipient.phone_third.as("phone_third")
        ))
            .from(order)
                .join(delivery)
                .on(order.id.eq(delivery.id))
                .where(order.id.eq(id))
                .fetchOne();

    }

    public List<OrderConfirmListResponse> order_confirmList(Long id) {
        return queryFactory.select(Projections.fields(OrderConfirmListResponse.class,
                order.id.as("order_id"),
                order.order_price.as("order_price"),
                order.modifiedDate.as("date_time"),
                order.orderStatus.as("order_status"),
                orderDetail.as("orderDetail")
        ))
            .from(order)
                .join(orderDetail)
                .on(order.id.eq(orderDetail.order.id))
                .where(order.user_id.eq(id))
                .fetch();
    }

    public List<Order> orderList(Long user_id) {
        return queryFactory.selectFrom(order)
                .where(order.user_id.eq(user_id))
                .fetch();
    }
}
