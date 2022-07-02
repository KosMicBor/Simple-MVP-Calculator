package kosmicbor.justsimplecalculator

import kosmicbor.justsimplecalculator.domain.CalculatorImpl
import kosmicbor.justsimplecalculator.domain.Operations
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalculationsUnitTest {

    private val calculator = CalculatorImpl()

    @Test
    fun calculator_CorrectAddOperation_ReturnsTrue() {
        assertEquals("4,00", calculator.calculateResult(2.0, 2.0, Operations.ADD))
    }

    @Test
    fun calculator_CorrectSubOperation_ReturnsTrue() {
        assertEquals("0,00", calculator.calculateResult(3.0, 3.0, Operations.SUB))
    }

    @Test
    fun calculator_CorrectMultiplyOperation_ReturnsTrue() {
        assertEquals("9,00", calculator.calculateResult(3.0, 3.0, Operations.MULTIPLY))
    }

    @Test
    fun calculator_CorrectDivideOperation_ReturnsTrue() {
        assertEquals("1,00", calculator.calculateResult(3.0, 3.0, Operations.DIVIDE))
    }

    @Test
    fun calculator_CorrectRounding_ReturnsTrue() {
        assertNotEquals(
            calculator.calculateResult(2.009, 2.0, Operations.ADD),
            calculator.calculateResult(2.0, 2.0, Operations.ADD)
        )
    }

    @Test
    fun calculator_ResultNotNull_ReturnsTrue() {
        assertNotNull(calculator.calculateResult(null, null, null))
    }

    @Test
    fun calculator_CorrectIncreaseListValuesByNumber_ReturnsTrue() {

        val newList = intArrayOf(1, 2, 3)

        val resultList = calculator.increaseListValuesByNumber(newList, 2)

        val expectedList = intArrayOf(3, 4, 5)

        assertArrayEquals(expectedList, resultList)
    }
}