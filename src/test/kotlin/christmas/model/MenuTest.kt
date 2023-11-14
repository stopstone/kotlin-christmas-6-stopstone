package christmas.model

import christmas.validator.ValidateOrder.checkCountZeroMenu

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class MenuTest {

    @ParameterizedTest
    @DisplayName("메뉴를 입력받을 떄 쉼표 구분자가 없거나 다를 경우 예외처리")
    @ValueSource(strings = ["타파스-1.제로콜라-1"])
    fun menuDelimiterTest(input: String) {
        assertThrows<IllegalArgumentException> {
            createMenuItem(input)
        }
    }

    @ParameterizedTest
    @DisplayName("메뉴를 구분하고, 메뉴와 개수를 구분하는 구분자(-)가 아닌경우 예외처리")
    @ValueSource(strings = ["타파스%1,제로콜라-1", "타파스1,제로콜라-1", "1-타파스,제로콜라-1"])
    fun menuCountTest(input: String) {
        assertThrows<IllegalArgumentException> {
            createMenuItem(input)
        }
    }

    @ParameterizedTest
    @DisplayName("메뉴의 개수가 숫자가 아닌 다른 값이 입력될 경우 예외처리")
    @ValueSource(strings = ["타파스-하나,제로콜라-1"])
    fun menuCountNotIntegerTest(input: String) {
        assertThrows<IllegalArgumentException> {
            createMenuItem(input)
        }
    }

    @ParameterizedTest
    @DisplayName("메뉴의 개수가 0일 경우 예외처리")
    @ValueSource(strings = ["타파스-0,제로콜라-1"])
    fun checkCountZeroMenuTest(input: String) {
        assertThrows<IllegalArgumentException> {
            checkCountZeroMenu(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["타파스-19,제로콜라-2"])
    @DisplayName("메뉴의 총 개수의 합이 20이 넘는 경우 예외처리")
    fun checkLimitOrderMenuTest(input: String) {
        assertThrows<IllegalArgumentException> {
            createMenuItem(input)
        }
    }

    @ParameterizedTest
    @ValueSource(strings = ["코카콜라-1"])
    @DisplayName("입력받은 메뉴가 메뉴판에 없는 경우 예외처리")
    fun findMenuItemsTest(input: String) {
        assertThrows<IllegalArgumentException> {
            findMenuItems(input)
        }
    }

}