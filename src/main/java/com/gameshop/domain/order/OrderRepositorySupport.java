package com.gameshop.domain.order;

import com.gameshop.domain.order.dto.OrderListResponse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gameshop.domain.order.QOrder.order;

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

    public Long find_order_ready(Long user_id) {
        return queryFactory.selectFrom(order)
                .where(order.user_id.eq(user_id))
                .fetchCount();
    }

    public List<Order> find_order_ready_total_price(Long user_id) {
        return queryFactory.selectFrom(order)
                .where(order.user_id.eq(user_id)
                    .and(order.orderStatus.eq(OrderStatus.READY))).fetch();


    }
}
