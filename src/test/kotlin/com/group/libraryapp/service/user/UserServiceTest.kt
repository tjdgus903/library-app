package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

// Service 계층이기 때문에 SpringBootTest 어노테이션 추가
@SpringBootTest
class UserServiceTest @Autowired constructor(
    // UserService 를 테스트하기 위해서 의존성을 추가해줘야됨
    private val userRepository: UserRepository,
    private val userService: UserService,
    private val userLoanHistoryRepository: UserLoanHistoryRepository
    ) {

    @AfterEach
    @DisplayName("각 test 종료 후 clean")
    fun clean(){
        println("CLEAN 시작")
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저 저장 정상 동작 확인")
    fun saveUserTest(){
        // given
        val request = UserCreateRequest("짭짭이", null)

        // when
        userService.saveUser(request)

        // then
        val results = userRepository.findAll()
        // AssertionsForInterfaceTypes 로 import 해줘야됨
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("짭짭이")
        // null 허용을 위해 @Nullable 어노테이션 붙혀야됨
        assertThat(results[0].age).isNull()
    }

    @Test
    @DisplayName("유저 조회 정상 동작 확인")
    fun getUserTest(){
        // given
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", null),
        ))

        // when
        val results = userService.getUsers()

        // then
        assertThat(results).hasSize(2)
        // A와 B 의 이름 체크
        // assertThat(객체).extracting(비교 변수 명).containsExactlyInAnyOrder(비교 기준 변수 명)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A", "B")
        assertThat(results).extracting("age").containsExactlyInAnyOrder(20, null)
    }

    @Test
    @DisplayName("유저 업데이트 정상 동작 확인")
    fun updateUserNameTest(){
        // given
        val savedUser = userRepository.save(User("A", null))
        val request = UserUpdateRequest(savedUser.id!!, "B")

        // when
        userService.updateUserName(request)

        // then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제 정상 동작 확인")
    fun deleteUserTest(){
        // given
        userRepository.save(User("A", null))

        // when
        userService.deleteUser("A")

        // then
        assertThat(userRepository.findAll()).isEmpty()
    }

    @Test
    @DisplayName("대출 기록이 없는 유저도 응답에 포함된다")
    fun getUserLoanHistories(){
        // given
        userRepository.save(User("A", null))

        // when
        val results = userService.getUserLoanHistories()

        // then
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).isEmpty()
    }

    @Test
    @DisplayName("대출 기록이 많은 유저의 응답이 정상 동작한다")
    fun getUserLoanHistories2(){
        // given
        val savedUser = userRepository.save(User("A", null))
        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(savedUser, "책1", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUser, "책2", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUser, "책3", UserLoanStatus.RETURNED),
        ))

        // when
        val results = userService.getUserLoanHistories()

        // then
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).hasSize(3)
        // books 안의 조회할 변수의 명칭을 입력하여 데이터 값 조회
        assertThat(results[0].books).extracting("name")
            .containsExactlyInAnyOrder("책1", "책2", "책3")
        assertThat(results[0].books).extracting("isReturn")
            .containsExactlyInAnyOrder(false, false, true)
    }


    @Test
    // 하지만 복잡한 테스트 하나보다 간단한 테스트 2개가 유지보수하기 용이함
    // 테스트는 기능 하나당 하나씩 하는 것이 직관적으로 분석하기 좋음
    @DisplayName("위 두 경우가 합쳐진 테스트")
    fun getUserLoanHistories3(){
        // given
        val savedUsers = userRepository.saveAll(listOf(
            User("A", null),
            User("B", null),
        ))

        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(savedUsers[0], "책1", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUsers[0], "책2", UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUsers[0], "책3", UserLoanStatus.RETURNED),
        ))

        // when
        val results = userService.getUserLoanHistories()

        // then
        assertThat(results).hasSize(2)
        val userAResult = results.first { it.name == "A"}

        assertThat(results[0].books).hasSize(3)
        // books 안의 조회할 변수의 명칭을 입력하여 데이터 값 조회
        assertThat(results[0].books).extracting("name")
            .containsExactlyInAnyOrder("책1", "책2", "책3")
        assertThat(results[0].books).extracting("isReturn")
            .containsExactlyInAnyOrder(false, false, true)

        val userBResult = results.first { it.name == "B" }
        assertThat(userBResult.books).isEmpty()
    }
}