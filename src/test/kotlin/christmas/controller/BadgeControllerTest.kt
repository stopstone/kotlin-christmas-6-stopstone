package christmas.controller

import christmas.domain.Date
import christmas.view.OutputView
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BadgeControllerTest {
    @ParameterizedTest
    @DisplayName("혜택 금액에 따라 뱃지가 정상적으로 부여되는지 확인")
    @ValueSource(ints = [0, 5000, 10000, 20000])
    fun readDateNumberEmptyTest(input: Int) {
        val badgeController = BadgeController(SaleController(Date("1")), OutputView())
        println(badgeController.choiceBadge(input))
    }
}