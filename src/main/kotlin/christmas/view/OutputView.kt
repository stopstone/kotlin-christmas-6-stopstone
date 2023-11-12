package christmas.view

import christmas.utils.Messages.INPUT_VISIT_DATE_MESSAGE
import christmas.utils.Messages.ORDER_MENU_MESSAGE
import christmas.utils.Messages.PREVIEW_EVENT_MESSAGE
import christmas.utils.Messages.WELCOME_RESTAURANT

class OutputView {
    fun printWelcomeRestaurant() {
        println(WELCOME_RESTAURANT)
    }

    fun inputVisitDateMessage() {
        println(INPUT_VISIT_DATE_MESSAGE)
    }

    fun printOrderToMenu() {
        println(ORDER_MENU_MESSAGE)
    }

    fun printPreviewEvent(dateNumber: Int) {
        println(PREVIEW_EVENT_MESSAGE.format(dateNumber))
    }

    fun printOrderMenuMessage() {
        println("<주문 메뉴>")
    }

    fun printBeforeTotalAmount() {
        println("<할인 전 총주문 금액>")
    }
    fun printBlank() = println()
}