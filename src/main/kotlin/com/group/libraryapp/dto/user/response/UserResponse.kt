package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

// dto 클래스들이 모두 class 타입으로 변경이 됨
// dto 는 data 클래스로 변경하는 것이 디버깅이나 값 출력에 더 유리함(개인 선호도 차이)
data class UserResponse(
    val id: Long,
    val name: String,
    val age: Int?,
) {

    // 정적 팩토리 메서드(of)를 사용하는 것이 가장 좋음
    // 정적 팩토리 메서드를 사용하면 의도를 명확히 할 수 있고
    // 필요 시 원하는 방향으로 커스텀 또한 가능
    companion object{
        fun of(user:User) : UserResponse{
            return UserResponse(
                id = user.id!!,
                name = user.name,
                age = user.age
            )
        }
    }

    // 부 생성자는 주 생성자를 불러서 선언
    /*constructor(user: User) : this(
        // !! 는 user.id 를 선언했을 때 초기에는 null 일 수 있기 때문에
        id = user.id!!,
        name = user.name,
        age = user.age
    )*/

    // 아래 init 으로 변수들을 선언하는 것보다는
    // 위 constructor 로 각각 변수 선언이 나음
    /*init {
        id = user.id!!
        name = user.name
        age = user.age
    }*/
}
