package christmas.model

import christmas.utils.Messages.ERROR_MESSAGE
import christmas.utils.Messages.INVALID_ORDER_MESSAGE
import christmas.validator.ValidateOrder.checkLimitOrderMenu
import christmas.validator.ValidateOrder.checkMinOrderMenu

enum class Menu(val itemCategory: String, val itemName: String, val itemPrice: Int) {
    APPETIZER_MUSHROOM_SOUP("애피타이저","양송이수프", 6000),
    APPETIZER_TAPAS("애피타이저","타파스", 5500),
    APPETIZER_CAESAR_SALAD("애피타이저","시저샐러드", 8000),

    MAIN_T_BONE_STEAK("메인","티본스테이크", 55000),
    MAIN_BBQ_RIB("메인","바비큐립", 54000),
    MAIN_SEAFOOD_PASTA("메인","해산물파스타", 35000),
    MAIN_CHRISTMAS_PASTA("메인","크리스마스파스타", 25000),

    DESSERT_CHOCOLATE_CAKE("디저트","초코케이크", 15000),
    DESSERT_ICE_CREAM("디저트","아이스크림", 5000),

    DRINK_ZERO_COLA("음료","제로콜라", 3000),
    DRINK_RED_WINE("음료","레드와인", 60000),
    DRINK_CHAMPAGNE("음료","샴페인", 25000)
}

fun createMenuItem(item: String): MenuItem {
    try {
        val (menu, count) = item.split("-")
        return MenuItem(findMenuItems(menu), count.toInt())
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
    } catch (e: IndexOutOfBoundsException) {
        throw IndexOutOfBoundsException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
    }
}

fun createMenuItems(items: List<String>): List<MenuItem> {
    val menuItems = mutableListOf<MenuItem>()

    if (checkMinOrderMenu(items)) {
        for (item in items) {
            val menuItem = createMenuItem(item)
            menuItems.add(menuItem)
        }
    }
    checkLimitOrderMenu(menuItems)
    return menuItems
}

fun findMenuItems(menuName: String): Menu {
    return Menu.entries.find { it.itemName == menuName }
        ?: throw IllegalArgumentException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
}

