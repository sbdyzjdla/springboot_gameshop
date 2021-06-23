package com.gameshop.domain.cart;

import com.gameshop.domain.qnas.Qnas;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.gameshop.domain.cart.QCart.cart;

@Repository
public class CartRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CartRepositorySupport(JPAQueryFactory queryFactory) {
        super(Cart.class);
        this.queryFactory = queryFactory;
    }

    public List<Cart> findAllUser(Long user_id) {
        return queryFactory.selectFrom(cart)
                .where(cart.user_id.eq(user_id)
                .and(cart.cartDelYn.eq(CartDelYn.NO)))
                .fetch();
    }
}
