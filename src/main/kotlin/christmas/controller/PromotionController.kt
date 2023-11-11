package christmas.controller


import christmas.model.Date
import christmas.model.MenuItem
import christmas.model.createMenuItems
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
    fun promotionStart() {
        val dateNumber = readDateNumber()
        orderMenu()
        discountPrice(dateNumber)
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

    private fun discountPrice(dateNumber: Int) {
        val saleController = SaleController()
        saleController.saleOFWeekendOrWeekday(dateNumber, menuItems)
    }
}