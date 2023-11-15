package christmas.domain

import christmas.repository.MenuItem
import christmas.utils.Constants.CHAMPAGNE_COST
import christmas.utils.Constants.NOTHING_DISCOUNT
import christmas.utils.Constants.PRESENT_AMOUNT
import christmas.utils.Constants.PRESENT_DISCOUNT
import christmas.validator.UserCaution.isOrderAmountValid
import christmas.view.OutputView

class PromotionResult(
    private val menuItems: List<MenuItem>,
    private val outputView: OutputView,
    date: Date
) {

    private var payment = Payment(date)
    private var eventBadge = EventBadge(payment)

    init {
        outputView.printPreviewEvent(date.getDate())
        menuSettings()
    }

    private fun menuSettings() {
        val minOrderPrice = isOrderAmountValid(menuItems)
        orderMenuDetail()
        beforeDiscountAmount()
        presentEventMenu(minOrderPrice)
        discountDetail(minOrderPrice)
        totalDiscountAmount(minOrderPrice)
        afterDiscountAmount(minOrderPrice)
        outputView.printEventBadge(eventBadge.processDiscountEventBadge(), minOrderPrice)
    }

    private fun orderMenuDetail() {
        outputView.printOrderMenuMessage()
        outputView.printOrderDetail(menuItems)
    }

    private fun beforeDiscountAmount() {
        val totalAmount = calculateTotalAmount()
        outputView.beforeDiscountTotalAmount(totalAmount)
    }

    private fun calculateTotalAmount(): Int {
        return TotalAmount(menuItems).getTotalAmount()
    }

    private fun presentEventMenu(minOrderPrice: Boolean) {
        val champagne = getChampagne(menuItems) / CHAMPAGNE_COST
        outputView.printPresentMenu(champagne, minOrderPrice)
    }

    private fun getChampagne(menuItems: List<MenuItem>): Int {
        val totalAmount = TotalAmount(menuItems)
        if (totalAmount.getTotalAmount() >= PRESENT_AMOUNT) {
            return PRESENT_DISCOUNT
        }
        return NOTHING_DISCOUNT
    }

    private fun discountDetail(minOrderPrice: Boolean) {
        val discountDetails = payment.saleStart(menuItems)
        outputView.printDiscountDetail(discountDetails, minOrderPrice)
    }

    private fun totalDiscountAmount(minOrderPrice: Boolean) {
        val totalDiscount = payment.totalDiscountAmount()
        outputView.printTotalDiscountPrice(totalDiscount, minOrderPrice)
    }

    private fun afterDiscountAmount(minOrderPrice: Boolean) {
        val champagneCost = getChampagne(menuItems)
        val totalDiscount = minOrderDiscount(minOrderPrice, payment.totalDiscountAmount())
        val totalAmount = TotalAmount(menuItems).getTotalAmount() + champagneCost

        outputView.printDiscountAfterPrice(totalAmount - totalDiscount)
    }

    private fun minOrderDiscount(minOrderPrice: Boolean, totalDiscount: Int): Int {
        if (!minOrderPrice) return 0
        return totalDiscount
    }
}
