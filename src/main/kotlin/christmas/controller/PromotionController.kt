package christmas.controller

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
        inputView.inputDate()
    }
}