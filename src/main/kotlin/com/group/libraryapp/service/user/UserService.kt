package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.util.fail
import com.group.libraryapp.util.findByIdOrThrow
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService (
    private val userRepository: UserRepository,
){

    @Transactional
    fun saveUser(request: UserCreateRequest){
        val newUser = User(request.name, request.age)
        userRepository.save(newUser)
    }

    @Transactional(readOnly = true)
    fun getUsers(): List<UserResponse> {
        return userRepository.findAll()
            .map { user -> UserResponse(user) }
            // UserResponse(it) 으로도 가능
    }

    @Transactional
    fun updateUserName(request: UserUpdateRequest){
        // Spring과 다르게 Kotlin 에서는 생성자를 불러올 때 :: 을 앞에 설정함
        // Optional<T> findById(ID id); 여기는 JPA 에서 Optional 으로 지정이 되어있음
        // val user = userRepository.findById(request.id).orElseThrow(::IllegalArgumentException)
        // 하지만 코틀린을 활용한 함수가 있으니
        // fun <T, ID> CrudRepository<T, ID>.findByIdOrNull(id: ID): T? = findById(id).orElse(null)
        // 이걸 활용하면 Optional을 주지 않고도 exception 처리가 가능함
        // 그리고 이걸 다시 ExceptionUtils 에서 변수 선언하여 사용하면 더 줄일 수 있음
        val user = userRepository.findByIdOrThrow(request.id)
        user.updateName(request.name)
    }

    @Transactional
    fun deleteUser(name: String){
        // 원래 코드
        // val user = userRepository.findByName(name).orElseThrow(::IllegalArgumentException)
        // findByName 에서 이제 Optional을 부르지 않기 때문에 orElseThrow 를 부를 수 없음
        // Optional을 사용하지 않기 때문에 ?: 를 통해 null 처리를 진행
        // fail() 함수를 ExceptionUtils 에 셋팅하여 throw IllegalArgumentException() 를 단축
        val user = userRepository.findByName(name) ?: fail()
        userRepository.delete(user)
    }

}