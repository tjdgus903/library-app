package com.group.libraryapp

import org.junit.jupiter.api.*

class JunitTest {

    // 코틀린에서는 companion이 static 과 같은 역할을 함
    // beforeAll, afterAll 은 companion 안에 들어가야 됨
    // static 에 포함시켜야되는 이유는 BeforeAll 과
    // AfterAll 둘 다 딱 1회만 실행되기 때문에 static으로 지정
    companion object {
        @BeforeAll
        @JvmStatic
        fun beforeAll(){
            println("모든 테스트 시작 전")
        }

        @AfterAll
        @JvmStatic
        fun afterAll(){
            println("모든 테스트 종료 후")
        }
    }


    @BeforeEach
    fun beforeEach(){
        println("BeforeEach 는 각 테스트 시작 전 수행")
    }

    @AfterEach
    fun afterEach(){
        println("AfterEach 는 각 테스트 종료 후")
    }

    @Test
    fun test1(){
        println("Test는 수행하고자 하는 테스트")
    }

    @Test
    fun test2(){
        println("테스트 2")
    }
}