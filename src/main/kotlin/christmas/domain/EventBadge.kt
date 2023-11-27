package christmas.domain

import christmas.model.Badge

class EventBadge(private val payment: Payment) {
    internal fun choiceBadge(totalDiscount: Int): String {
        val selectedBadge = Badge.entries.firstOrNull { totalDiscount >= it.threshold } ?: Badge.NONE
        return selectedBadge.displayBadge
    }

    fun processDiscountEventBadge(): String {
        return calculateTotalDiscount().let(::choiceBadge)
    }

    private fun calculateTotalDiscount(): Int {
        return payment.totalDiscountAmount()
    }
}