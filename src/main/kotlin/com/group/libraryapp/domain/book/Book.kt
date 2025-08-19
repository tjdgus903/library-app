package com.group.libraryapp.domain.book

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book (
    val name: String,

    // 책의 유형은 한번 지정되면 바뀌지 않아서 val로 지정
    val type: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
){

    init{
        if(name.isBlank()){
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }

    // 생성자를 만들어서 계속 사용하는 것 보다 정적 메소드를 만들어 활용하는 것이
    // dto 나 domain 코드값이 변경되었을 때 활용하기 훨신 수월함
    // 만약 테스트코드에 Book("책","COMPUTER",null)
    // 이렇게 생성하는 코드가 여러군데 있으면 모두 다 바꿔야되지만
    // fixture 로 하나 선언하여 고쳐두면 이거만 수정함으로써 해결 가능
    // companion object 는 맨 하단에 선언하는 것이 정석
    companion object{
        fun fixture(
            // 값들은 default 값들을 넣어줌
            name: String = "책 이름",
            type: String = "COMPUTER",
            id: Long? = null,
        ): Book{
            return Book(
                name = name,
                type = type,
                id = id,
            )
        }
    }
}
