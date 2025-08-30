package com.group.libraryapp.repository.book

import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class BookQuerydslRepository(
    private val queryFactory: JPAQueryFactory,
) {

    // 아래의 형식과 같이도 querydsl 을 사용할 수 있음
    // => select type, count(book.id) from book group by type;
    fun getStats(): List<BookStatResponse>{
        return queryFactory
            .select(
                // Projections.constructor 은 첫번째로 선언한 class 에 생성자를 부름
                // 주어진 dto 의 생성자를 호출한다는 의미
                Projections.constructor(
                    BookStatResponse::class.java,
                    book.type,
                    book.id.count()
            )
        )
        .from(book)
        .groupBy(book.type)
        .fetch()
    }
}