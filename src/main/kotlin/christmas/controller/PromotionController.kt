package christmas.controller


import christmas.utils.Messages
import christmas.view.InputView
import christmas.view.OutputView

class PromotionController(private val inputView: InputView, private val outputView: OutputView) {
    init {
        outputView.printWelcomeRestaurant()
    }

    fun promotionStart() {
        readDateNumber()
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

    private fun validate(input: String?): Int {
        require(!input.isNullOrEmpty() && input.all { it.isDigit() }) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_MESSAGE}"}
        require(input.toInt() in 1..31) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_MESSAGE}"}
        return input.toInt()
    }
}