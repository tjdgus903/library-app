package com.group.libraryapp.dto.book.request

data class BookRequest(
    val name: String,
    val type: String,

    // DTO 도 Entity 처럼 companion object 같이
    // 정적 객체를 만들어서 활용 가능하지만
    // 이것 또한 프로젝트나 팀 분위기, 상황에 맞춰서 활용 권장
)
