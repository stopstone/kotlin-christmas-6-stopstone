package christmas.validator

import christmas.model.MenuItem
import christmas.utils.Constants
import christmas.utils.ErrorMessage.ERROR_MESSAGE
import christmas.utils.ErrorMessage.INVALID_ORDER_MESSAGE

object UserCaution {
    internal fun checkLimitOrderMenu(menuItems: List<MenuItem>) {
        val limit = menuItems.sumOf { it.count }
        require(limit <= Constants.ORDER_LIMIT) {"$ERROR_MESSAGE $INVALID_ORDER_MESSAGE"}
    }

    internal fun checkOrderOnlyDrink(menuItems: List<MenuItem>) {
        require(menuItems.any { it.menu.itemCategory != Constants.DRINK }) {"$ERROR_MESSAGE $INVALID_ORDER_MESSAGE"}
    }
}