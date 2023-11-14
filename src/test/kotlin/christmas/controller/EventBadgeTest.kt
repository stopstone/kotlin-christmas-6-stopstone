package christmas.controller

import christmas.domain.Date
import christmas.domain.EventBadge
import christmas.domain.Payment
import christmas.view.OutputView
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class EventBadgeTest {
    @ParameterizedTest
    @DisplayName("혜택 금액에 따라 뱃지가 정상적으로 부여되는지 확인")
    @ValueSource(ints = [0, 5000, 10000, 20000])
    fun readDateNumberEmptyTest(input: Int) {
        val eventBadge = EventBadge(Payment(Date("1")))
        println(eventBadge.choiceBadge(input))
    }
}