package com.group.libraryapp.calculator

// 테스트 클래스 수행 시 main 함수를 생성하여 수행
fun main(){
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiplyTest()
}

// test 코드는 기존 사용하는 디렉토리 클래스와 경로를 동일하게 맞춘 뒤
// 클래스 명칭 뒤에 Test 를 붙히는게 테스트 코드의 관례
class CalculatorTest {

    fun addTest(){
        // given / when / then 패턴

        // given - 어떠한 테스트를 하는지 준비
        val calculator = Calculator(5)

        // when - test 하고자 하는 기능 호출
        calculator.add(3)

        // then - 테스트 결과를 검증
        if(calculator.number != 8){
            throw IllegalStateException()
        }
    }

    fun minusTest(){
        // given
        val calculator = Calculator(5)

        // when
        calculator.minus(3)

        // then
        if(calculator.number != 2){
            throw IllegalStateException()
        }
    }

    fun multiplyTest(){
        // given
        var calculator = Calculator(5)

        // when
        calculator.multiply(3)

        // then
        if(calculator.number != 15){
            throw IllegalStateException()
        }
    }
}