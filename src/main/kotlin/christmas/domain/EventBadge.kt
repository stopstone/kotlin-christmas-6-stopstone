package christmas.domain

import christmas.model.Badge
import christmas.view.OutputView

class EventBadge(private val payment: Payment, private val outputView: OutputView) {
    internal fun choiceBadge(totalDiscount: Int): String {
        val selectedBadge = Badge.entries.firstOrNull { totalDiscount >= it.threshold } ?: Badge.NONE
        return selectedBadge.displayBadge
    }


    fun processDiscountEventBadge() {
        calculateTotalDiscount().let(::choiceBadge).also(::printEventBadge)
    }


    private fun calculateTotalDiscount(): Int {
        return payment.totalDiscountAmount()
    }


    private fun printEventBadge(badgeKind: String) {
        outputView.printEventBadge(badgeKind)
    }
}