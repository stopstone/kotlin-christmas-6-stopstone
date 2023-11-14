package christmas.controller


import christmas.domain.Date
import christmas.model.createMenuItems
import christmas.model.*
import christmas.utils.ErrorMessage.ERROR_MESSAGE
import christmas.utils.ErrorMessage.INVALID_ORDER_MESSAGE
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
        MenuController(menuItems, outputView, date)
    }


    private fun readDateNumber() {
        outputView.inputVisitDateMessage()
        while (true) {
            try {
                date = Date(inputView.inputDate())
                break
            } catch (e: IllegalArgumentException) {
                throw IllegalArgumentException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
            } catch (e: NumberFormatException) {
                throw NumberFormatException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
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