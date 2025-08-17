package com.group.libraryapp.calculator

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JunitCalculatorTest {

    @Test
    fun addTest(){
        // given
        val calculator = Calculator(5)

        // when
        calculator.add(3)

        // then
        // Junit 에서는 assertThat 을 통해 값을 체크하여
        // console에서 실제 값과 오차를 확인할 수 있음
        // assertThat(확인하고자 하는 값).isEqualTo(기대값)
        assertThat(calculator.number).isEqualTo(8)

        // 주어진 값이 true 인지 false 인지 검증
        assertThat(calculator.number == 8).isTrue
        assertThat(calculator.number == 7).isFalse()
    }

    @Test
    fun minusTest(){
        // given
        val calculator = Calculator(5)

        // when
        calculator.minus(3)

        // then
        assertThat(calculator.number).isEqualTo(2)
    }


    @Test
    fun multiplyTest(){
        // given
        val calculator = Calculator(5)

        // when
        calculator.multiply(3)

        // then
        assertThat(calculator.number).isEqualTo(15)
    }


    @Test
    fun divideTest(){
        // given
        val calculator = Calculator(5)

        // when
        calculator.divide(3)

        // then
        assertThat(calculator.number).isEqualTo(1)
    }

    @Test
    fun divideExceptionTest(){
        // given
        val calculator = Calculator(5)

        // when & then
        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message
        assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")

        // 아래와 같이 바꿀 수 있음
        assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply {
            assertThat(message).isEqualTo("0으로 나눌 수 없습니다.")
        }
    }

}