package christmas.controller


import christmas.model.*
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

    private fun discountPrice(dateNumber: Int, menuItems: List<MenuItem>): Int {
        val saleController = SaleController()
        val eachDiscount = saleController.saleStart(dateNumber, menuItems)
        totalDiscount = saleController.totalDiscountAmount(eachDiscount)

        return totalDiscount
    }

    private fun choiceBadge(totalDiscount: Int) {
        val badge = Badge()
        badge.selectBadge(totalDiscount)
    }

    private fun printResult(dateNumber: Int) {
        outputView.printPreviewEvent(dateNumber)
    }
}