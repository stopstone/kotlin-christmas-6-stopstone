package christmas.domain

import christmas.repository.MenuItem
import christmas.utils.Constants
import christmas.utils.Constants.CHAMPAGNE_COST
import christmas.utils.Constants.MIN_ORDER_COST
import christmas.view.OutputView

class PromotionResult(
    private val menuItems: List<MenuItem>,
    private val outputView: OutputView,
    date: Date
) {

    private lateinit var totalAmount: TotalAmount
    private var payment = Payment(date)
    private var eventBadge = EventBadge(payment, outputView)

    init {
        outputView.printPreviewEvent(date.getDate())
        menuSettings()
    }

    private fun menuSettings() {
        val minOrderPrice = TotalAmount(menuItems).getTotalAmount() >= MIN_ORDER_COST
        orderMenuDetail()
        presentEventMenu(minOrderPrice)
        beforeDiscountAmount()
        discountDetail(minOrderPrice)
        totalDiscountAmount(minOrderPrice)
        afterDiscountAmount()
        eventBadge.processDiscountEventBadge()
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
        val champagne = getChampagne(menuItems)
        outputView.printPresentMenu(champagne, minOrderPrice)
    }


    private fun getChampagne(menuItems: List<MenuItem>): Int {
        totalAmount = TotalAmount(menuItems)
        return totalAmount.getTotalAmount() / Constants.PRESENT_AMOUNT
    }


    private fun discountDetail(minOrderPrice: Boolean) {
        val discountDetails = payment.saleStart(menuItems)
        outputView.printDiscountDetail(discountDetails, minOrderPrice)
    }


    private fun totalDiscountAmount(minOrderPrice: Boolean) {
        val totalDiscount = payment.totalDiscountAmount()
        outputView.printTotalDiscountPrice(totalDiscount, minOrderPrice)
    }


    private fun afterDiscountAmount() {
        val totalPriceAfterDiscount = calculateTotalPriceAfterDiscount()
        outputView.printDiscountAfterPrice(totalPriceAfterDiscount)
    }


    private fun calculateTotalPriceAfterDiscount(): Int {
        val totalAmount = TotalAmount(menuItems).getTotalAmount()
        val champagneCost = getChampagne(menuItems) * CHAMPAGNE_COST
        val totalDiscount = payment.totalDiscountAmount()
        return totalAmount + champagneCost - totalDiscount
    }
}
