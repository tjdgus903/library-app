package com.group.libraryapp.util

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.findByIdOrNull

fun fail(): Nothing {
    throw IllegalArgumentException()
}

// 기존의 CrudRepository 를 가져와서 확장함수로 선언하여 코드를 단축시킬 수 있음
fun <T, ID> CrudRepository<T, ID>.findByIdOrThrow(id:ID): T{
    return this.findByIdOrNull(id) ?: fail()
}