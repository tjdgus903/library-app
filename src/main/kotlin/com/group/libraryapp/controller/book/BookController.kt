package com.group.libraryapp.controller.book

import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.service.book.BookService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BookController (
    private val bookService: BookService,
){
/*
    멱등성이란 결과를 n번 보내도 결과가 같으면 것
    | HTTP Method | 스프링 어노테이션   | 역할(REST 관점)        멱등성    |
    | ----------- | ---------------- | ----------------    | ------ |
    | GET         | `@GetMapping`    | 리소스 조회           | O      |
    | POST        | `@PostMapping`   | 리소스 생성           | X      |
    | PUT         | `@PutMapping`    | 리소스 전체 수정/교체  | O      |
    | PATCH       | `@PatchMapping`  | 리소스 일부 수정      | X (보통) |
    | DELETE      | `@DeleteMapping` | 리소스 삭제           | O      |
*/

    @PostMapping("/book")
    fun saveBook(@RequestBody request: BookRequest){
        bookService.saveBook(request)
    }

    @PostMapping("/book/loan")
    fun loanBook(@RequestBody request: BookLoanRequest){
        bookService.loanBook(request)
    }

    @PutMapping("/book/return")
    fun returnBook(@RequestBody request: BookReturnRequest){
        bookService.returnBook(request)
    }
}