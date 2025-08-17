package com.group.libraryapp.domain.user.loanhistory

import org.springframework.data.jpa.repository.JpaRepository

interface UserLoanHistoryRepository : JpaRepository<UserLoanHistory, Long>{

    // 값이 들어갈 수도, 안들어갈 수도 있기 때문에 ? 를 붙힘
    fun findByBookNameAndIsReturn(bookName: String, isReturn: Boolean) : UserLoanHistory?

}