package christmas.model

import christmas.utils.Messages.ERROR_MESSAGE
import christmas.utils.Messages.INVALID_ORDER_MESSAGE
import christmas.validator.ValidateOrder
import christmas.validator.ValidateOrder.checkMinOrderMenu

enum class Menu {
}


fun createMenuItems(items: List<String>): List<MenuItem> {
    val menuItems = mutableListOf<MenuItem>()

    if(checkMinOrderMenu(items)) {
        for (item in items) {
            val (menu, count) = item.split("-")
            val menuItem = MenuItem(getMenu(menu), count.toInt())
            menuItems.add(menuItem)
        }
    }
    return menuItems
}

fun getMenu(menuString: String): Menu {
    return try {
        Menu.valueOf(menuString)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
    }
}