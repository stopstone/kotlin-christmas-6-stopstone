package christmas.validator

import christmas.model.MenuItem
import christmas.utils.Messages

object ValidateOrder {
    internal fun checkDuplicateMenu(input: String): List<String> {
        val menuItems = input.split(",")
        val menuSet = menuItems.map { it.split("-").first() }.toSet()
        if (menuSet.size < menuItems.size) {
            throw IllegalArgumentException("${Messages.ERROR_MESSAGE} ${Messages.INVALID_ORDER_MESSAGE}")
        }
        return menuItems
    }

    internal fun checkMinOrderMenu(menuItems: List<String>): Boolean {
        require(menuItems.isNotEmpty()) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_ORDER_MESSAGE}"}
        return true
    }

    internal fun checkLimitOrderMenu(menuItems: List<MenuItem>) {
        val limit = menuItems.sumOf { it.count }
        require(limit <= 20) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_ORDER_MESSAGE}"}
    }

}