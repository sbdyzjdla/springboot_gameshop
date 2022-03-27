package com.gameshop.domain.qnas;

import com.gameshop.domain.qnas.dto.CommentResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.gameshop.domain.qnas.QComment.comment;
import static com.gameshop.domain.user.QUser.user;

@Repository
public class CommentRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;

    public CommentRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Comment.class);
        this.queryFactory = jpaQueryFactory;
    }

    public List<CommentResponseDto> findAllQnas(Long qnas_id) {
        return queryFactory.select(Projections.fields(CommentResponseDto.class,
                    comment.id.as("comment_id"),
                    comment.modifiedDate.as("comment_date"),
                    comment.content.as("content"),
                    user.id.as("user_id"),
                    user.email.as("email"),
                    user.name.as("name"),
                    user.picture.as("picture"),
                    user.role.as("role")
                ))
                .from(comment)
                    .leftJoin(user)
                        .on(comment.user.id.eq(user.id))
                    .where(comment.qnas.id.eq(qnas_id))
                .fetch();
    }

}
