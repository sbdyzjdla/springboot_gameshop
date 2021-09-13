package com.gameshop.domain.products.titles;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.gameshop.domain.products.titles.QTitles.titles;

@Repository
public class TitlesRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public TitlesRepositorySupport(JPAQueryFactory queryFactory) {
        super(Titles.class);
        this.queryFactory = queryFactory;
    }

    public QueryResults<Titles> findAllNS(Pageable pageable) {
        return queryFactory.selectFrom(titles)
                .where(titles.console.eq("닌텐도스위치"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }

    public QueryResults<Titles> findAllPS(Pageable pageable) {
        return queryFactory.selectFrom(titles)
                .where(titles.console.eq("PS5"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
    }
}
