package christmas.validator

import christmas.model.MenuItem
import christmas.utils.Constants
import christmas.utils.Messages

object UserCaution {
    internal fun checkLimitOrderMenu(menuItems: List<MenuItem>) {
        val limit = menuItems.sumOf { it.count }
        require(limit <= Constants.ORDER_LIMIT) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_ORDER_MESSAGE}"}
    }

    internal fun checkOrderOnlyDrink(menuItems: MutableList<MenuItem>) {
        require(menuItems.any { it.menu.itemCategory != Constants.DRINK }) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_ORDER_MESSAGE}"}
    }
}