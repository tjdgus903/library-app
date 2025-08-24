package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.*

// 코틀린을 통해 repository를 구성하면 () 에 변수를 선언하는 것 만으로도
// 기존에 getter, setter 를 선언하여 사용하는 것 처럼 카멜 형식의 get, set을 사용 할 수 있음
@Entity
class UserLoanHistory (
    @ManyToOne
    val user: User,

    val bookName: String,

    // Boolean 을 값으로 설정하는 것은 추천하지 않음
    // Boolean 이 한개이면 상관이 없지만 2개, 3개가
    // 넘어갈 경우 관리 포인트가 너무 많이 늘어나기 때문
    // var isReturn: Boolean,
    // 이럴 때 Enum 을 활용하면 코드 유지보수가 용이해짐
    var status: UserLoanStatus = UserLoanStatus.LOANED,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
){

    val isReturn: Boolean
        get() = this.status == UserLoanStatus.RETURNED

    fun doReturn(){
        this.status = UserLoanStatus.RETURNED
    }

    // 전체 Entity 조회를 하기 위해서
    // companion object 로 fixture 선언
    companion object{
        fun fixture(
            user: User,
            bookName: String = "이상한 나라의 엘리스",
            status: UserLoanStatus = UserLoanStatus.LOANED,
            id: Long? = null,
        ): UserLoanHistory {
            return UserLoanHistory(
                user = user,
                bookName = bookName,
                status = status,
                id = id,
            )
        }
    }
}