package christmas.controller

import christmas.utils.Messages.ERROR_MESSAGE
import christmas.utils.Messages.INVALID_MESSAGE
import christmas.view.InputView
import christmas.view.OutputView
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DateNumberTest {
    @Test
    fun `날짜 입력값에 아무 값이 없을 경우`() {
        try {
            val promotionController = PromotionController(InputView(), OutputView())
             promotionController.validate("")
        } catch (e: IllegalArgumentException) {
            println(e.message)
            assertEquals("$ERROR_MESSAGE $INVALID_MESSAGE", e.message)
        }
    }

    @Test
    fun `날짜 입력값에 1에서 31이 아닌 다른 숫자가 들어왔을 경우`() {
        try {
            val promotionController = PromotionController(InputView(), OutputView())
            promotionController.validate("42")
        } catch (e: IllegalArgumentException) {
            println(e.message)
            assertEquals("$ERROR_MESSAGE $INVALID_MESSAGE", e.message)
        }
    }

    @Test
    fun `날짜 입력값에 숫자가 아닌 문자가 들어왔을 경우`() {
        try {
            val promotionController = PromotionController(InputView(), OutputView())
            promotionController.validate("우아한")
        } catch (e: IllegalArgumentException) {
            println(e.message)
            assertEquals("$ERROR_MESSAGE $INVALID_MESSAGE", e.message)
        }
    }
}