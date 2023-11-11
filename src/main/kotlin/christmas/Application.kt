package christmas

import christmas.controller.PromotionController
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val promotionController = PromotionController(InputView(), OutputView())
    promotionController.promotionStart()
}
