package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
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

    // Query 를 통해 sql 을 수행할 수 있으며
    // DISTINCT 를 통해 중복 데이터 제거 가능
    // FETCH 를 통해 join을 한번만 사용할 수 있음
    // 이것을 FETCH JOIN 이라고 함
    // FETCH JOIN - JPA 에서 한번의 쿼리로 연관 데이터까지 한번에 조회하는 기능
    // User와 userLoanHistories 를 같이 조회하여 영속성 컨텍스트에 로드
    // FETCH JOIN 을 사용하지 않으면 userLoanHistories 을 조회할 때 추가 SELECT 를 해야하지만
    // FETCH JOIN 을 통해 조회하면 영속성 컨텍스트에 데이터가 쌓여 추가 쿼리가 필요하지 않음
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.userLoanHistories")
    fun findAllWithHistories(): List<User>

}