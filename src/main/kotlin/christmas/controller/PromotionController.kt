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
        //readDateNumber()
        orderMenu()
    }

    private fun readDateNumber() {
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
        date.calculateDay(dateNumber.toInt())
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

    private fun discountPrice() {
        val saleController = SaleController()
        saleController.menuPriceCal(menuItems)
    }
}