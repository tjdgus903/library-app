package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.user.QUser.user
import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.querydsl.jpa.impl.JPAQueryFactory

class UserRepositoryCustomImpl(
    // dsl을 사용하기 위하여 queryDsl 을 선언한 bean 주입
    private val queryFactory: JPAQueryFactory,
) : UserRepositoryCustom{

    // : 를 통해 UserRepositoryCustom 을 참조하고 있기 때문에
    // 기존 UserRepositoryCustom 의 함수를 override
    override fun findAllWithHistories(): List<User> {
        return queryFactory.select(user).distinct()
            .from(user)
            .leftJoin(userLoanHistory).on(userLoanHistory.user.id.eq(user.id)).fetchJoin()
            .fetch()    // 쿼리를 수행한다
    }
}