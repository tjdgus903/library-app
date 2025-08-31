package com.group.libraryapp.service

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.service.user.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class TempTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
){

    /*
        테스트를 할 때 영속성 컨텍스트를 사용할 수 있는 방법
        1. @Transacrional 어노테이션 붙혀서 사용하기(권장)
            - 장점
              간결하고 롤백 가능
              병렬 테스트 가능
            - 단점
              테스트 내성이 떨어짐
              (- Service단에서 Transactional 을 지우면 서비스 동작에서 에러가 발생,
               - JPA 에서는 save 한 오브젝트가 영속성 컨텍스트에만 존재하고 db로 flush 되지 않은 상태로
                 rollback 되기 때문에 명시적으로 flush 하지 않으면 실제 db 매핑에 문제가 있을 수 있음)

        2. N쪽의 Repository 활용
            - 장점
              Transactional 을 안 쓸 수 있다
            - 단점
              선언되어있는 값들을 모두 작성하여 테스트 해야됨(번거로움)

        3. fetch join 을 사용
            - 장점
              fetch join 을 활용하여 기존 테스트와 동일하게 가능
            - 단점
              두개의 테이블을 조회 못함(각각 사용)

    */

    @Transactional
    @Test
    @DisplayName("유저 1명과 책 2권을 저장하고 대출한다")
    fun oneAndTwoTest() {
        // when
        userService.saveUserAndLoanTwoBooks()

        // then
        // val users = userRepository.findAll()
        // 3번 캐이스
        val users = userRepository.findAllWithHistories()
        assertThat(users).hasSize(1)
        assertThat(users[0].userLoanHistories).hasSize(2)

        // 1번 캐이스
        // assertThat(users[0].userLoanHistories).hasSize(2)

        // 2번 캐이스
        // val histories = userLoanHistoryRepository.findAll()
        // assertThat(histories).hasSize(2)
        // assertThat(histories[0].user.id).isEqualTo(users[0].id)

    }
}