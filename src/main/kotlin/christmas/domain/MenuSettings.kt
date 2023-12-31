package christmas.domain

import christmas.model.Menu
import christmas.repository.MenuItem
import christmas.utils.Constants.ITEMS_DELIMITER
import christmas.utils.ErrorMessage.ERROR_MESSAGE
import christmas.utils.ErrorMessage.INVALID_ORDER_MESSAGE
import christmas.validator.UserCaution.checkLimitOrderMenu
import christmas.validator.UserCaution.checkOrderOnlyDrink
import christmas.validator.ValidateOrder.checkCountZeroMenu
import christmas.validator.ValidateOrder.checkMinOrderMenu


class MenuSettings {
    fun createMenuItem(item: String): MenuItem {
        try {
            val (menu, count) = item.split(ITEMS_DELIMITER)

            return MenuItem(findMenuItems(menu), count.toInt())
        } catch (e: IllegalArgumentException) {
            throw IllegalArgumentException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
        } catch (e: IndexOutOfBoundsException) {
            throw IndexOutOfBoundsException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
        }
    }

    fun createMenuItems(items: List<String>): List<MenuItem> {
        val menuItems = items.map { createMenuItem(it) }
        checkMenuItems(menuItems)

        return menuItems
    }

    private fun checkMenuItems(menuItems: List<MenuItem>) {
        checkCountZeroMenu(menuItems)
        checkMinOrderMenu(menuItems)
        checkLimitOrderMenu(menuItems)
        checkOrderOnlyDrink(menuItems)
    }

    fun findMenuItems(menuName: String): Menu {
        return Menu.entries.find { it.itemName == menuName }
            ?: throw IllegalArgumentException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
    }
}