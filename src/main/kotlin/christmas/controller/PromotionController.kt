package christmas.controller


import christmas.validator.ValidateDate.validate
import christmas.validator.ValidateOrder.checkDuplicateMenu
import christmas.view.InputView
import christmas.view.OutputView

class PromotionController(private val inputView: InputView, private val outputView: OutputView) {
    init {
        outputView.printWelcomeRestaurant()
    }

    fun promotionStart() {
        readDateNumber()
        orderMenu()
    }

    private fun readDateNumber() {
        outputView.inputVisitDateMessage()
        while (true) {
            val dateNumber = inputView.inputDate()
            try {
                validate(dateNumber)
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
                val order = inputView.inputMenu()
                checkDuplicateMenu(order)
                break
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }



}