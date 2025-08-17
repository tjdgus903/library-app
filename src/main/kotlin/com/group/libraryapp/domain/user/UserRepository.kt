package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

// interface를 선언할 때 JpaRepository<> 를 사용한다고 가정하면
// <> 에는 각각 엔티티 클래스와 id 의 타입이 들어가야됨
// User 의 Id 값은 Long 이기 때문에 UserRepository 의 <> 에는
// 각각 User, Long 이 들어가야 됨
interface UserRepository : JpaRepository<User, Long>{

    fun findByName(name: String): Optional<User>

}