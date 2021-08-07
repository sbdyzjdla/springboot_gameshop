package com.gameshop.domain.cart;

import com.gameshop.domain.cart.dto.CartProdListResDto;
import com.gameshop.domain.qnas.Qnas;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.gameshop.domain.cart.QCart.cart;
import static com.gameshop.domain.cart.QCartProducts.cartProducts;

@Repository
public class CartRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CartRepositorySupport(JPAQueryFactory queryFactory) {
        super(Cart.class);
        this.queryFactory = queryFactory;
    }

    public List<Cart> findAllUser(Long user_id) {
        return queryFactory.selectFrom(cart).rightJoin(cartProducts)
                .on(cart.id.eq(cartProducts.id))
                .where(cart.user_id.eq(user_id))
                .fetch();
    }

    public List<CartProdListResDto> findAllCartUser(Long user_id) {
        return queryFactory.select(Projections.fields(CartProdListResDto.class,
                cartProducts.id.as("cart_products_id"),
                cart.id.as("cart_id"),
                cartProducts.orderPrice.as("order_price"),
                cartProducts.p_price.as("p_price"),
                cartProducts.quantity.as("quantity"),
                cartProducts.products.img_num.as("img_num"),
                cartProducts.products.p_name.as("p_name"),
                cartProducts.products.quantity.as("max_quantity")
                ))
        .from(cart)
                .join(cartProducts)
                .on(cart.id.eq(cartProducts.cart.id))
                .where(cart.user_id.eq(user_id))
                .fetch();
    }

    public CartProdListResDto CartProdFindById(Long id) {
        return queryFactory.select(Projections.fields(CartProdListResDto.class,
                cartProducts.id.as("cart_products_id"),
                cartProducts.orderPrice.as("order_price"),
                cartProducts.p_price.as("p_price"),
                cartProducts.quantity.as("quantity"),
                cartProducts.products.img_num.as("img_num"),
                cartProducts.products.p_name.as("p_name")
        ))
                .from(cartProducts)
                .where(cartProducts.id.eq(id))
                .fetchOne();
    }

    public Optional<Cart> findByUserId(Long user_id) {
        return Optional.ofNullable(queryFactory.selectFrom(cart)
                .where(cart.user_id.eq(user_id))
                .fetchOne());
    }

    public Long findByCart(Long user_id) {
        return queryFactory.selectFrom(cart)
                .where(cart.user_id.eq(user_id))
                .fetchCount();
    }

    public CartProducts cartProdEntityFindById(Long id) {
        return queryFactory.selectFrom(cartProducts)
                .where(cartProducts.id.eq(id))
                .fetchOne();
    }

    public List<CartProducts> findByOrderId(Long order_id) {
        return queryFactory.selectFrom(cartProducts)
                .where(cartProducts.order.id.eq(order_id))
                .fetch();
    }
}
