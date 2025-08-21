package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

// Entity 영역에서는 data class 를 참조하지 않는 것을 권장
@Entity
// constructor 을 쓰지 않아도 되지만 작성하고 클릭해보면
// 해당 Entity가 쓰이는 로직들을 확인하고 추적 가능
class User constructor(
    // 생성자 안에서 쓰이지 않고 class body 안에서 쓰일 때에는
    // 시작할 때 사용되는 init 블록에서 변수들을 검증하기 때문에
    // 초기화되지 않은 상태라면 NPE 오류 발생시킬 수 있음
    // 그렇기 때문에 초기화를 해주는 것이 좋음
    // class body에서 쓸 때에는 아래 주석처럼 같이 사용
    // name 은 null 처리 안됨, "" 값으로 초기화 상태
    // var name: String = ""
    var name: String,

    // 만약 class body에서 사용한다면 아래 주석처럼 같이 사용
    // age 는 null 허용, 초기값 null 로 초기화 상태
    // val age: Int? = null,
    val age: Int?,

    // 프로퍼티를 생성자 () 안에 설정해도 되고 class body 안에 설정해도 정상 동작
    // 다만 생성자 안에 넣는 쪽으로 진행을 했기 때문에 class body에 넣으면 해당 부분들을 바꿔줘야됨
    // 이것은 정해진 것이 없기 때문에 각각 프로젝트 규율에 따라 가면 됨
    // 이전 강의에서 들었던 김영한의 jpa 에서는 모두 body 에 선언함
    @OneToMany(mappedBy="user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val userLoanHistoreis: MutableList<UserLoanHistory> = mutableListOf(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
){

    init{
        if(name.isBlank()){
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }

    fun updateName(name:String){
        this.name = name
    }

    fun loanBook(book: Book){
        this.userLoanHistoreis.add(UserLoanHistory.fixture(this, book.name))
    }

    fun returnBook(bookName: String){
        this.userLoanHistoreis.first{ history -> history.bookName == bookName}.doReturn()
    }

}