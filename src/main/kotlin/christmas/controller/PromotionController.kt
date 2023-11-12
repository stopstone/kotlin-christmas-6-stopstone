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

        orderDetails()
        discountBeforePrice()
        val champagne = presentEvent()
        discountDetail()
        totalDiscountPrice()
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

    private fun presentEvent(): Int {
        val champagne = totalAmount / 120000
        outputView.printPresentMenu(champagne)
        outputView.printBlank()
        return champagne
    }

    private fun discountDetail() {
        val items = Sale.entries.map { it.saleName }

        outputView.printDiscountDetail(eachDiscount, items)
        outputView.printBlank()
    }

    private fun totalDiscountPrice() {
        outputView.printTotalDiscountPrice(totalDiscount)
        outputView.printBlank()
    }

    private fun discountAfterPrice(champagne: Int) {
        val present = champagne * 25000
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