package christmas.controller

import christmas.view.OutputView

class PromotionController(private val outputView: OutputView) {
    init {
        outputView.printWelcomeRestaurant()
    }
}