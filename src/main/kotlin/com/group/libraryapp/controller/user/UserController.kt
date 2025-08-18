package com.group.libraryapp.controller.user

import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.service.user.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController (
    private val userService: UserService
){
    @PostMapping("/user")
    fun saveUser(@RequestBody request: UserCreateRequest){
        userService.saveUser(request)
    }

    @GetMapping("/user")
    fun getUser() : List<UserResponse> = userService.getUsers()
    // 원래 아래가 자바의 정석이지만 코틀린은 위 방식으로도 가능(함수 문법)
    /*fun getUser() : List<UserResponse> {
        return userService.getUsers()
    }*/

    @PutMapping("/user")
    fun updateUserName(@RequestBody request: UserUpdateRequest){
        userService.updateUserName(request)
    }

    @DeleteMapping("/user")
    // @RequestParam 을 사용할 때 사용 변수를 ? 를 붙혀
    // nullable(null 가능) 하게끔 하면 required() 값을 false로 바꿈
    // required() 는 RequestParam 함수에서 확인 가능
    // 그러므로 동일하게 변수 사용하는게 좋음
    fun deleteUser(@RequestParam name: String){
        userService.deleteUser(name)
    }

}