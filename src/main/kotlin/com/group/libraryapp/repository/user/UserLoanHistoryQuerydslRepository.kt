package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.querydsl.core.QueryFactory
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class UserLoanHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {

    // 매개변수 영역에서 UserLoanStatus 값의 기본 값을 ?(null 가능), null 로 주면
    // 값이 있을 때는 where 절에 걸려 ?.let 를 타 eq 비교를 하게 되고 없으면 안타게 됨
    fun find(bookName: String, status: UserLoanStatus? = null): UserLoanHistory?{
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
            .where(
                userLoanHistory.bookName.eq(bookName),
                status?.let { userLoanHistory.status.eq(status) }
            )
            .limit(1)
            .fetchOne()
    }

    fun count(status: UserLoanStatus): Long {
        return queryFactory.select(userLoanHistory.count())
            .from(userLoanHistory)
            .where(
                userLoanHistory.status.eq(status)
            )
            .fetchOne() ?: 0L   // 비어있는 캐이스가 있을 수 있어 없을 경우 0 반환
    }
}