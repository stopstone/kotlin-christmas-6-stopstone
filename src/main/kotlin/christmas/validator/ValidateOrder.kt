package christmas.validator

import christmas.model.MenuItem
import christmas.utils.Constants.DRINK
import christmas.utils.Constants.ITEMS_DELIMITER
import christmas.utils.Constants.MENU_DELIMITER
import christmas.utils.Constants.ORDER_LIMIT
import christmas.utils.Messages.ERROR_MESSAGE
import christmas.utils.Messages.INVALID_ORDER_MESSAGE

object ValidateOrder {
    internal fun checkDuplicateMenu(input: String): List<String> {
        val menuItems = input.split(MENU_DELIMITER)
        val menuSet = menuItems.map { it.split(ITEMS_DELIMITER).first() }.toSet()
        if (menuSet.size < menuItems.size) {
            throw IllegalArgumentException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
        }
        return menuItems
    }

    internal fun checkCountZeroMenu(count: String) {
        require(count.toInt() != 0) {"$ERROR_MESSAGE $INVALID_ORDER_MESSAGE"}
    }

    internal fun checkMinOrderMenu(menuItems: List<String>) {
        require(menuItems.isNotEmpty()) {"$ERROR_MESSAGE $INVALID_ORDER_MESSAGE"}
    }
}