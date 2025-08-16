package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

// Service 계층이기 때문에 SpringBootTest 어노테이션 추가
@SpringBootTest
class UserServiceTest @Autowired constructor(
    // UserService 를 테스트하기 위해서 의존성을 추가해줘야됨
    private val userRepository: UserRepository,
    private val userService: UserService
    ) {

    @Test
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

}