package christmas.controller


import christmas.model.*
import christmas.utils.Constants.CHAMPAGNE_COST
import christmas.utils.Constants.MIN_ORDER_COST
import christmas.utils.Constants.NOTHING
import christmas.utils.Constants.PRESENT_AMOUNT
import christmas.validator.ValidateDate.validate
import christmas.validator.ValidateOrder.checkDuplicateMenu
import christmas.view.InputView
import christmas.view.OutputView

class PromotionController(private val inputView: InputView, private val outputView: OutputView) {
    init {
        outputView.printWelcomeRestaurant()
    }

    private lateinit var date: Date
    private lateinit var menuItems: List<MenuItem>
    private lateinit var saleController: SaleController
    private var totalAmount = 0
    private var totalDiscount = 0
    private lateinit var eachDiscount: MutableList<Int>

    fun promotionStart() {
        val dateNumber = readDateNumber()
        orderMenu()
        discountPrice(dateNumber, menuItems)
        choiceBadge(totalDiscount)
        printResult(dateNumber)

    }

    private fun readDateNumber(): Int {
        outputView.inputVisitDateMessage()
        var dateNumber: String
        while (true) {
            dateNumber = inputView.inputDate()
            date = Date()
            try {
                validate(dateNumber)
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: NumberFormatException) {
                println(e.message)
            }
        }
        return dateNumber.toInt()
    }

    private fun orderMenu() {
        outputView.printOrderToMenu()
        while (true) {
            try {
                val order = inputView.inputMenu()
                val items = checkDuplicateMenu(order)
                menuItems = createMenuItems(items)
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: IndexOutOfBoundsException) {
                println(e.message)
            }
        }

    }

    private fun discountPrice(dateNumber: Int, menuItems: List<MenuItem>) {
        saleController = SaleController()
        eachDiscount = saleController.saleStart(dateNumber, menuItems)
        totalAmount = saleController.totalOrderAmount(menuItems)
        totalDiscount = saleController.totalDiscountAmount(eachDiscount)

    }

    private fun choiceBadge(totalDiscount: Int): String {
        val badge = Badge()
        return badge.selectBadge(totalDiscount)
    }

    private fun printResult(dateNumber: Int) {
        outputView.printPreviewEvent(dateNumber)
        outputView.printBlank()

        val minOrderPrice = totalAmount >= MIN_ORDER_COST

        orderDetails()
        discountBeforePrice()
        val champagne = presentEvent(minOrderPrice)
        discountDetail(minOrderPrice)
        totalDiscountPrice(minOrderPrice)
        discountAfterPrice(champagne)
        eventBadge()
    }

    private fun orderDetails() {
        outputView.printOrderMenuMessage()
        outputView.printOrderDetail(menuItems)
        outputView.printBlank()
    }

    private fun discountBeforePrice() {
        outputView.printBeforeAmountMessage()
        outputView.beforeDiscountTotalAmount(totalAmount)
        outputView.printBlank()
    }

    private fun presentEvent(minOrderPrice: Boolean): Int {
        val champagne = totalAmount / PRESENT_AMOUNT
        outputView.printPresentMenu(champagne, minOrderPrice)
        outputView.printBlank()
        return champagne
    }

    private fun discountDetail(minOrderPrice: Boolean) {
        val items = Sale.entries.map { it.saleName }
        if (minOrderPrice) {
            discountDetails(items)
        }
        if (!minOrderPrice) {
            outputView.printDiscountDetail(NOTHING)
        }
        outputView.printBlank()
    }

    private fun discountDetails(items: List<String>) {
        for (idx in eachDiscount.indices) {
            if (eachDiscount[idx] != 0) {
                outputView.printDiscountDetail("${items[idx]} ${eachDiscount[idx]}")
            }
        }
    }

    private fun totalDiscountPrice(minOrderPrice: Boolean) {
        if (!minOrderPrice) {
            totalDiscount = 0
        }
        outputView.printTotalDiscountPrice(totalDiscount)
        outputView.printBlank()
    }

    private fun discountAfterPrice(champagne: Int) {
        val present = champagne * CHAMPAGNE_COST
        totalAmount += present
        outputView.printDiscountAfterPrice(totalAmount, totalDiscount)
        outputView.printBlank()
    }

    private fun eventBadge() {
        val badgeKind = choiceBadge(totalDiscount)
        outputView.printEventBadge(badgeKind)
        outputView.printBlank()
    }
}