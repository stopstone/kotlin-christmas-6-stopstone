package christmas.controller


import christmas.model.*
import christmas.utils.Constants
import christmas.utils.Constants.MIN_ORDER_COST
import christmas.utils.Constants.WON
import christmas.utils.StringFormatter.formatNumberWithComma
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
    private var totalAmount = 0
    private var totalDiscount = 0
    private lateinit var eachDiscount: MutableList<Int>

    fun promotionStart() {
        readDateNumber()
        orderMenu()
        discountPrice(date.getDate())
        choiceBadge(totalDiscount)
        printResult()
    }

    private fun readDateNumber() {
        outputView.inputVisitDateMessage()
        while (true) {
            date = Date(inputView.inputDate())
            try {
                validate(date.getDate().toString())
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: NumberFormatException) {
                println(e.message)
            }
        }
    }

    private fun orderMenu() {
        outputView.printOrderToMenu()
        while (true) {
            try {
                val items = checkDuplicateMenu(inputView.inputMenu())
                menuItems = createMenuItems(items)
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: IndexOutOfBoundsException) {
                println(e.message)
            }
        }

    }

    private fun discountPrice(dateNumber: Int) {
        val saleController = SaleController()
        eachDiscount = saleController.saleStart(dateNumber, menuItems)
        totalAmount = saleController.totalOrderAmount(menuItems)
        totalDiscount = saleController.totalDiscountAmount(eachDiscount)

    }

    private fun choiceBadge(totalDiscount: Int): String {
        val selectedBadge = Badge.entries.firstOrNull { totalDiscount >= it.threshold } ?: Badge.NONE
        return selectedBadge.displayBadge
    }

    private fun printResult() {
        outputView.printPreviewEvent(date.getDate())
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
    }

    private fun discountBeforePrice() {
        outputView.printBeforeAmountMessage()
        outputView.beforeDiscountTotalAmount(totalAmount)
    }

    private fun presentEvent(minOrderPrice: Boolean): Int {
        val champagne = totalAmount / Constants.PRESENT_AMOUNT
        outputView.printPresentMenu(champagne, minOrderPrice)
        return champagne
    }

    private fun discountDetail(minOrderPrice: Boolean) {
        val items = Sale.entries.map { it.saleName }
        if (minOrderPrice) {
            discountDetails(items)
        }
        if (!minOrderPrice) {
            outputView.printDiscountDetail(Constants.NOTHING)
        }
    }

    private fun discountDetails(items: List<String>) {
        for (idx in eachDiscount.indices) {
            if (eachDiscount[idx] != 0) {
                outputView.printDiscountDetail("${items[idx]} ${formatNumberWithComma(eachDiscount[idx])}$WON")
            }
        }
    }

    private fun totalDiscountPrice(minOrderPrice: Boolean) {
        if (!minOrderPrice) {
            totalDiscount = 0
        }
        outputView.printTotalDiscountPrice(totalDiscount)
    }

    private fun discountAfterPrice(champagne: Int) {
        val present = champagne * Constants.CHAMPAGNE_COST
        totalAmount += present
        outputView.printDiscountAfterPrice(totalAmount, totalDiscount)
    }

    private fun eventBadge() {
        val badgeKind = choiceBadge(totalDiscount)
        outputView.printEventBadge(badgeKind)
    }

}