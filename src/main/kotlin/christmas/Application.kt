package christmas

import christmas.controller.PromotionManager
import christmas.view.InputView
import christmas.view.OutputView

fun main() {
    val promotionManager = PromotionManager(InputView(), OutputView())
    promotionManager.promotionStart()
}
