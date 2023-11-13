package christmas.view

import christmas.model.MenuItem
import christmas.model.SaleItem
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
import christmas.utils.StringFormatter.formatNumberWithComma

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
        menuItems.forEach { item ->
            println("${item.menu.itemName} ${item.count}$SOME_COUNT")
        }
        printBlank()
    }

    fun printPreviewEvent(dateNumber: Int) {
        println(PREVIEW_EVENT_MESSAGE.format(dateNumber))
    }

    fun printOrderMenuMessage() {
        printBlank()
        println(ORDER_MENU)
    }

    fun printBeforeAmountMessage() {
        println(BEFORE_DISCOUNT_AMOUNT)
    }

    fun beforeDiscountTotalAmount(totalAmount: Int) {
        println("${formatNumberWithComma(totalAmount)}${WON}")
        printBlank()
    }

    fun printPresentMenu(champagne: Int, minOrderPrice: Boolean) {
        println(PRESENT_MENU)
        if (minOrderPrice) {
            println("$CHAMPAGNE ${champagne}${SOME_COUNT}")
        }
        if (!minOrderPrice) {
            println(NOTHING)
        }
        printBlank()
    }

    fun printDiscountDetail(discountDetails: MutableList<SaleItem>) {
        println(DISCOUNT_DETAIL)
        discountDetails
            .filter { it.discountAmount != 0 }
            .forEach { printSingleDiscountDetail(it) }
        printBlank()
    }

    private fun printSingleDiscountDetail(discountItem: SaleItem) {
        val formattedDiscount = formatNumberWithComma(discountItem.discountAmount * MINUS)
        println("${discountItem.sale.saleName}: $formattedDiscount$WON")
    }

    fun printTotalDiscountPrice(totalDiscount: Int) {
        println(TOTAL_DISCOUNT_PRICE)
        println("${formatNumberWithComma(totalDiscount * MINUS)}${WON}")
        printBlank()
    }

    fun printDiscountAfterPrice(totalAmount: Int, totalDiscount: Int) {
        println(AFTER_DISCOUNT_PRICE)
        println("${formatNumberWithComma(totalAmount-totalDiscount)}$WON")
        printBlank()
    }

    fun printEventBadge(badgeKind: String) {
        println(DECEMBER_EVENT_BADGE)
        println(badgeKind)
    }

    private fun printBlank() = println()
}
