package com.group.libraryapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LibraryAppApplication

// 코틀린에서는 탑 라인에 여러 함수와 여러 코드를 선언할 수 있고
// 함수를 선언하면 static 같은 지역 함수처럼 감지됨
fun main(args: Array<String>){
    runApplication<LibraryAppApplication>(*args)
}