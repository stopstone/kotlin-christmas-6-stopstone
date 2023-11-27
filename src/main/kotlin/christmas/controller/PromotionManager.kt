package christmas.controller


import christmas.domain.Date
import christmas.domain.MenuSettings
import christmas.domain.PromotionResult
import christmas.repository.MenuItem
import christmas.validator.ValidateOrder.checkDuplicateMenu
import christmas.view.InputView
import christmas.view.OutputView

class PromotionManager(private val inputView: InputView, private val outputView: OutputView) {
    init {
        outputView.printWelcomeRestaurant()
    }

    fun promotionStart() {
        val dateNumber = readDateNumber()
        val menuItems = orderMenu()
        PromotionResult(menuItems, outputView, dateNumber)
    }

    private fun readDateNumber(): Date {
        outputView.inputVisitDateMessage()
        while (true) {
            try {
                return Date(inputView.inputDate())
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: NumberFormatException) {
                println(e.message)
            }
        }
    }

    private fun orderMenu(): List<MenuItem> {
        val menuSettings = MenuSettings()

        outputView.printOrderToMenu()
        while (true) {
            try {
                val items = checkDuplicateMenu(inputView.inputMenu())
                return menuSettings.createMenuItems(items)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: IndexOutOfBoundsException) {
                println(e.message)
            }
        }
    }
}