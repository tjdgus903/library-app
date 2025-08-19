package com.group.libraryapp.domain.book

// enum 클래스는 변수들의 종류를 정리하여 활용 가능
// 위치는 1. 지금처럼 domain 패키지 내에 선언
// 2. Entity 파일 하단에 설정
// 3. controller, domain 같은 상단에 만들어서 생성 가능함
enum class BookType {
    COMPUTER,
    ECONOMY,
    SOCIETY,
    LANGUAGE,
    SCIENCE,
}