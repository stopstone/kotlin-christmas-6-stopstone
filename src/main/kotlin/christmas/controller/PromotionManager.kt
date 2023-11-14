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

    private lateinit var date: Date
    private lateinit var menuItems: List<MenuItem>
    private val menuSettings = MenuSettings()

    fun promotionStart() {
        readDateNumber()
        orderMenu()
        PromotionResult(menuItems, outputView, date)

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
                menuItems = menuSettings.createMenuItems(items)
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            } catch (e: IndexOutOfBoundsException) {
                println(e.message)
            }
        }
    }
}