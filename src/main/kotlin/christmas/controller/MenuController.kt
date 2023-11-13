package christmas.controller

import christmas.domain.Date
import christmas.model.MenuItem
import christmas.domain.TotalAmount
import christmas.utils.Constants
import christmas.utils.Constants.CHAMPAGNE_COST
import christmas.view.OutputView

class MenuController(
    private val menuItems: List<MenuItem>,
    private val outputView: OutputView,
    private val date: Date, private val saleController: SaleController
) {

    private lateinit var totalAmount: TotalAmount

    init {
        outputView.printPreviewEvent(date.getDate())
        menuSettings()
    }
    private fun menuSettings() {
        orderMenuDetail()
        presentEventMenu()
        beforeDiscountAmount()
        discountDetail()
        totalDiscountAmount()
        afterDiscountAmount()
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

    private fun presentEventMenu() {
        val champagne = getChampagne(menuItems)
        outputView.printPresentMenu(champagne, true)
    }

    private fun getChampagne(menuItems: List<MenuItem>): Int {
        totalAmount = TotalAmount(menuItems)
        return totalAmount.getTotalAmount() / Constants.PRESENT_AMOUNT
    }

    private fun discountDetail() {
        val discountDetails = saleController.saleStart(date.getDate(), menuItems)
        outputView.printDiscountDetail(discountDetails)
    }

    private fun totalDiscountAmount() {
        val totalDiscount = saleController.totalDiscountAmount()
        outputView.printTotalDiscountPrice(totalDiscount)
    }

    private fun afterDiscountAmount() {
        totalAmount = TotalAmount(menuItems)
        val totalPrice = totalAmount.getTotalAmount() + (getChampagne(menuItems) * CHAMPAGNE_COST)
        val totalDiscount = saleController.totalDiscountAmount()
        outputView.printDiscountAfterPrice(totalPrice - totalDiscount)
    }
}