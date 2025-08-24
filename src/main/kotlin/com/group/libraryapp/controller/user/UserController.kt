package com.group.libraryapp.controller.user

import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.dto.user.response.UserLoanHistoryResponse
import com.group.libraryapp.dto.user.response.UserResponse
import com.group.libraryapp.service.book.BookService
import com.group.libraryapp.service.user.UserService
import org.springframework.web.bind.annotation.*

// Controller를 구분하는 3가지 기준(api 선언 방식)
// 1. 화면에서 사용되는 api 끼리 모아두기
// 2. 동일한 도메인끼리 api 모아두기
// 3. (간혹) 1 api, 1 Controller 사용

// Controller를 찾는 방법
// 1. api 를 알고있다는 가정 하에 전체 검색
// Ctrl + Shift + f (예시 : /user)
// 2. intelliJ 에서는 하단에 endpoint 를 통해
// 선언되어있는 api 들을 조회할 수 있음
// 3. api 를 따로 코틀린 페이지에 선언하여
// 각각 api들을 매핑시킴(여기에서는 이 방식 사용)

@RestController
// 전체 /user 적용시키는 어노테이션을 사용할 수 있지만
// 위 Controller 를 찾을 때 /user/loan/hello 처럼
// 긴 api 를 찾기 용이하기 위해서 PostMapping 으로
// 하나하나 api 위치와 경로 지정함
// @RequestMapping("/user")
class UserController (
    private val userService: UserService,
    private val bookService: BookService,
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

    @GetMapping("/user/loan")
    fun getUserLoanHistories(): List<UserLoanHistoryResponse>{
        return userService.getUserLoanHistories()
    }

    @GetMapping("/book/loan")
    fun countLoanedBook(): Int{
        return bookService.countLoanedBook()
    }

    @GetMapping("/book/stat")
    fun getBookStatistics(): List<BookStatResponse>{
        return bookService.getBookStatistics()
    }
}