package christmas.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DateTest {
    @ParameterizedTest
    @DisplayName("일자를 입력받지 못한 경우 예외가 발생한다.")
    @ValueSource(strings = [""])
    fun readDateNumberEmptyTest(input: String) {
        assertThrows<IllegalArgumentException> {
            Date(input)
        }
    }

    @ParameterizedTest
    @DisplayName("일자의 범위가 벗어난 경우 예외가 발생한다.")
    @ValueSource(strings = ["0", "32"])
    fun readDateNumberRangeTest(input: String) {
        assertThrows<IllegalArgumentException> {
            Date(input)
        }
    }

    @ParameterizedTest
    @DisplayName("일자가 숫자가 아닌 다른 형태로 입력될 경우")
    @ValueSource(strings = ["!", "Wooteco", "2:", "우아한"])
    fun readDateNumberTest(input: String) {
        assertThrows<IllegalArgumentException> {
            Date(input)
        }
    }

    @Test
    @DisplayName("일자를 입력하면 12월의 요일이 정상적으로 계산되어 나오는지 확인")
    fun calculateDayTest() {
        val date = Date("3")
        assertEquals(date.calculateDay(date.getDate()), 0)
    }

    @Test
    @DisplayName("일자가 정상적으로 받아지는지 확인")
    fun getDateNumberTest() {
        val date = Date("3")
        assertEquals(date.getDate(), 3)
    }
}