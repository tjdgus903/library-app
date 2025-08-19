package com.group.libraryapp.dto.book.request

import com.group.libraryapp.domain.book.BookType

data class BookRequest(
    val name: String,
    val type: BookType,

    // DTO 도 Entity 처럼 companion object 같이
    // 정적 객체를 만들어서 활용 가능하지만
    // 이것 또한 프로젝트나 팀 분위기, 상황에 맞춰서 활용 권장
)
