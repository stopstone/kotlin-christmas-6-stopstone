package christmas.view

import christmas.model.MenuItem
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

    fun printOrderDetail(menuItems: List<MenuItem>) {
        for (item in menuItems) {
            println("${item.menu.itemName} ${item.count}개")
        }
    }

    fun printPreviewEvent(dateNumber: Int) {
        println(PREVIEW_EVENT_MESSAGE.format(dateNumber))
    }

    fun printOrderMenuMessage() {
        println("<주문 메뉴>")
    }

    fun printBeforeAmountMessage() {
        println("<할인 전 총주문 금액>")
    }

    fun beforeDiscountTotalAmount(totalAmount: Int) {
        println("${totalAmount}원")
    }

    fun printPresentMenu(champagne: Int) {
        println("<증정 메뉴>")
        println("${champagne}개")
    }

    fun printDiscountDetail(eachDiscount: MutableList<Int>, items: List<String>) {
        println("<혜택 내역>")
        for (idx in eachDiscount.indices) {
            if (eachDiscount[idx] != 0) {
                println("${items[idx]}: -${eachDiscount[idx]}")
            }
        }
    }

    fun printBlank() = println()
}
