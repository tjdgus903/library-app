package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

// interface를 선언할 때 JpaRepository<> 를 사용한다고 가정하면
// <> 에는 각각 엔티티 클래스와 id 의 타입이 들어가야됨
// User 의 Id 값은 Long 이기 때문에 UserRepository 의 <> 에는
// 각각 User, Long 이 들어가야 됨
interface UserRepository : JpaRepository<User, Long>{

    // 스프링에서는 ? 가 있으면 값을 조회하여 값이 있으면 User,
    // 없으면 자동으로 null 이 들어가게끔 셋팅을 하고 있음
    // 스프링이 코틀린을 지원하기 때문에 호환되는 코드
    fun findByName(name: String): User?

}