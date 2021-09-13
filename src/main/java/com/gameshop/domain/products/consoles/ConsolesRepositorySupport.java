package com.gameshop.domain.products.consoles;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gameshop.domain.products.consoles.QConsoles.consoles;

@Repository
public class ConsolesRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public ConsolesRepositorySupport(JPAQueryFactory queryFactory) {
        super(Consoles.class);
        this. queryFactory = queryFactory;
    }

    public QueryResults<Consoles> findAllNint(Pageable pageable) {
        return queryFactory.selectFrom(consoles)
                .where(consoles.manufact.eq("닌텐도"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    public QueryResults<Consoles> findAllPs5(Pageable pageable) {
        return queryFactory.selectFrom(consoles)
                .where(consoles.manufact.eq("소니"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }
}
