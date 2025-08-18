package com.group.libraryapp.dto.user.request

// null 처리는 convert java file to kotlin file 으로
// 따로 처리가 안되기 때문에 ? 를 붙혀 널 가능 표시를 해줘야됨
data class UserCreateRequest(
    val name: String,
    val age: Int?
)
