package christmas.controller

import christmas.utils.Messages
import christmas.validator.ValidateDate
import christmas.validator.ValidateOrder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class OrderMenuTest {
    @Test
    fun `메뉴 입력에 중복된 메뉴가 있을 경우`() {
        try {
            ValidateOrder.checkDuplicateMenu("레드와인-1,레드와인-1")
        } catch (e: IllegalArgumentException) {
            println(e.message)
            Assertions.assertEquals("${Messages.ERROR_MESSAGE} ${Messages.INVALID_ORDER_MESSAGE}", e.message)
        }
    }
}