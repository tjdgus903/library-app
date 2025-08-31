# 📚 Kotlin + Spring Boot + JPA Backend Project

## 🛠 기술 스택
- **Language:** Kotlin, Java  
- **Framework:** Spring Boot, Spring Data JPA  
- **Query:** QueryDSL, JPQL  
- **Database:** MySQL  
- **Test:** Junit5, Spring Boot Test  
- **Etc:** Gradle, Git  

---


## 🎯 학습 및 구현 성과
- **Kotlin + JPA 환경**
  - Null 안정성, 불변 객체 처리, Lazy Loading 문제 해결
- **테스트**
  - Junit5 + Spring Boot Test로 단위/통합 테스트 작성
  - Fixture 패턴 적용
- **데이터 접근**
  - JPQL → QueryDSL 기반 동적 쿼리 구현 및 Repository 리팩토링
  - SQL Fetch Join으로 N+1 문제 해결 및 성능 최적화

---


## 📌 주요 기능

### 1. 기본 기능
- 도서 등록, 조회, 대출, 반납 API
- RESTful API 설계 및 Controller/Service/Repository 계층 분리

### 2. 추가 구현 기능
- 책 등록 시 분야 선택 기능
- 유저별 대출 현황 및 기록 조회
- 대여 중인 책 및 전체 등록 책 통계 제공
- JPQL → QueryDSL 리팩토링

---

## 🧩 설계 및 구현 경험
- 기능 추가 시 계층별 책임 분리와 모듈 구조 설계
- Enum + JPA 매핑 전략 적용 및 관리 방식 비교
- SQL Join 전략 비교 (Inner Join, Left Join, Fetch Join)
- 동일 기능을 DB와 애플리케이션 레벨에서 구현 후 성능 분석
- 함수형 프로그래밍 스타일 적용
- 설계 선택지별 장단점 정리 및 문서화


