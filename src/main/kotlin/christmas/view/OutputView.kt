package christmas.view

import christmas.model.MenuItem
import christmas.utils.Constants.CHAMPAGNE
import christmas.utils.Constants.MINUS
import christmas.utils.Constants.NOTHING
import christmas.utils.Constants.SOME_COUNT
import christmas.utils.Constants.WON
import christmas.utils.Messages.AFTER_DISCOUNT_PRICE
import christmas.utils.Messages.BEFORE_DISCOUNT_AMOUNT
import christmas.utils.Messages.DECEMBER_EVENT_BADGE
import christmas.utils.Messages.DISCOUNT_DETAIL
import christmas.utils.Messages.INPUT_VISIT_DATE_MESSAGE
import christmas.utils.Messages.ORDER_MENU
import christmas.utils.Messages.ORDER_MENU_MESSAGE
import christmas.utils.Messages.PRESENT_MENU
import christmas.utils.Messages.PREVIEW_EVENT_MESSAGE
import christmas.utils.Messages.TOTAL_DISCOUNT_PRICE
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
            println("${item.menu.itemName} ${item.count}${SOME_COUNT}")
        }
    }

    fun printPreviewEvent(dateNumber: Int) {
        println(PREVIEW_EVENT_MESSAGE.format(dateNumber))
    }

    fun printOrderMenuMessage() {
        println(ORDER_MENU)
    }

    fun printBeforeAmountMessage() {
        println(BEFORE_DISCOUNT_AMOUNT)
    }

    fun beforeDiscountTotalAmount(totalAmount: Int) {
        println("${totalAmount * MINUS}${WON}")
    }

    fun printPresentMenu(champagne: Int, minOrderPrice: Boolean) {
        println(PRESENT_MENU)
        if (minOrderPrice) {
            println("$CHAMPAGNE ${champagne}${SOME_COUNT}")
        }
        if (!minOrderPrice) {
            println(NOTHING)
        }
    }

    fun printDiscountDetail(discountDetail: String) {
        println(DISCOUNT_DETAIL)
        println(discountDetail)
    }

    fun printTotalDiscountPrice(totalDiscount: Int) {
        println(TOTAL_DISCOUNT_PRICE)
        println("${totalDiscount * MINUS}${WON}")
    }

    fun printDiscountAfterPrice(totalAmount: Int, totalDiscount: Int) {
        println(AFTER_DISCOUNT_PRICE)
        println(totalAmount-totalDiscount)
    }

    fun printEventBadge(badgeKind: String) {
        println(DECEMBER_EVENT_BADGE)
        println(badgeKind)
    }

    fun printBlank() = println()
}
