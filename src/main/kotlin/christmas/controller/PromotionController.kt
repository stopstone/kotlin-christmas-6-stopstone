package christmas.controller


import christmas.domain.Date
import christmas.repository.MenuItem
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
        readDateNumber()
        orderMenu()
        ResultController(menuItems, outputView, date)
    }


    private fun readDateNumber() {
        outputView.inputVisitDateMessage()
        while (true) {
            try {
                date = Date(inputView.inputDate())
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
}