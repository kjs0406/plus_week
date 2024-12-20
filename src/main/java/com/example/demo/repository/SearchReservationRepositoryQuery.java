package com.example.demo.repository;

import com.example.demo.entity.QItem;
import com.example.demo.entity.QReservation;
import com.example.demo.entity.QUser;
import com.example.demo.entity.Reservation;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchReservationRepositoryQuery {

    private final JPAQueryFactory queryFactory;

    private static final QReservation reservation = QReservation.reservation;
    private static final QUser user = QUser.user;
    private static final QItem item = QItem.item;

    public List<Reservation> searchReservations(Long userId, Long itemId) {
        return queryFactory.selectFrom(reservation)
                .join(reservation.user, user).fetchJoin()
                .join(reservation.item, item).fetchJoin()
                .where(Expressions.allOf(
                        validationOfUserIdConditions(userId), validationOfItemIdConditions(itemId)))
                .fetch();
    }

    private BooleanExpression validationOfUserIdConditions(Long userId){
        if (userId == null){
            return null;
        }

        return reservation.user.id.eq(userId);
    }

    private BooleanExpression validationOfItemIdConditions(Long itemId){
        if (itemId == null){
            return null;
        }

        return reservation.item.id.eq(itemId);
    }
}
