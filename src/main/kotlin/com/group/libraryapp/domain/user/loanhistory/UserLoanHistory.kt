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

    var isReturn: Boolean,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
){

    fun doReturn(){
        this.isReturn = true
    }

}