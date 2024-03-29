package com.gameshop.domain.qnas;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.gameshop.domain.qnas.QQnas.qnas;

@Repository
public class QnasRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public QnasRepositorySupport(JPAQueryFactory queryFactory) {
        super(Qnas.class);
        this.queryFactory = queryFactory;
    }

    public List<Qnas> findAllDesc() {
        return queryFactory.selectFrom(qnas)
                .orderBy(qnas.id.desc())
                .fetch();
    }

    public List<Qnas> findByTitle(String title, Pageable pageable) {
        return queryFactory.selectFrom(qnas)
                .where(qnas.title.contains(title))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
