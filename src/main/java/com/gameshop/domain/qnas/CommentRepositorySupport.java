package com.gameshop.domain.qnas;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.gameshop.domain.qnas.QComment.comment;

@Repository
public class CommentRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CommentRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Comment.class);
        this.queryFactory = jpaQueryFactory;
    }

    public List<Comment> findAllQnas(Long qnas_id) {
        return queryFactory.selectFrom(comment)
                .where(comment.qnas.id.eq(qnas_id))
                .fetch();
    }

}
