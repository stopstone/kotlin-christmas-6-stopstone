package christmas.controller

import christmas.domain.Date
import christmas.model.MenuItem
import christmas.domain.TotalAmount
import christmas.utils.Constants
import christmas.utils.Constants.CHAMPAGNE_COST
import christmas.utils.Constants.MIN_ORDER_COST
import christmas.view.OutputView

class MenuController(
    private val menuItems: List<MenuItem>,
    private val outputView: OutputView,
    private val date: Date
) {

    private lateinit var totalAmount: TotalAmount
    private var saleController = SaleController(date)
    private var badgeController = BadgeController(saleController, outputView)

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
        badgeController.eventBadge()
    }

    private fun orderMenuDetail() {
        outputView.printOrderMenuMessage()
        outputView.printOrderDetail(menuItems)
    }

    private fun beforeDiscountAmount() {
        totalAmount = TotalAmount(menuItems)
        outputView.printBeforeAmountMessage()
        outputView.beforeDiscountTotalAmount(totalAmount.getTotalAmount())
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
        val discountDetails = saleController.saleStart(menuItems)
        outputView.printDiscountDetail(discountDetails, minOrderPrice)
    }

    private fun totalDiscountAmount(minOrderPrice: Boolean) {
        val totalDiscount = saleController.totalDiscountAmount()
        outputView.printTotalDiscountPrice(totalDiscount, minOrderPrice)
    }

    private fun afterDiscountAmount() {
        totalAmount = TotalAmount(menuItems)
        val totalPrice = totalAmount.getTotalAmount() + (getChampagne(menuItems) * CHAMPAGNE_COST)
        val totalDiscount = saleController.totalDiscountAmount()
        outputView.printDiscountAfterPrice(totalPrice - totalDiscount)
    }


}
