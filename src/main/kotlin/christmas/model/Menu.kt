package christmas.model

import christmas.utils.Messages.ERROR_MESSAGE
import christmas.utils.Messages.INVALID_ORDER_MESSAGE
import christmas.validator.ValidateOrder.checkMinOrderMenu

enum class Menu(val itemName: String, val itemPrice: Int) {
    APPETIZER_MUSHROOM_SOUP("양송이수프", 6000),
    APPETIZER_TAPAS("타파스", 5500),
    APPETIZER_CAESAR_SALAD("시저샐러드", 8000),

    MAIN_T_BONE_STEAK("티본스테이크", 55000),
    MAIN_BBQ_RIB("바비큐립", 54000),
    MAIN_SEAFOOD_PASTA("해산물파스타", 35000),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타", 25000),

    DESSERT_CHOCOLATE_CAKE("초코케이크", 15000),
    DESSERT_ICE_CREAM("아이스크림", 5000),

    DRINK_ZERO_COLA("제로콜라", 3000),
    DRINK_RED_WINE("레드와인", 60000),
    DRINK_CHAMPAGNE("샴페인", 25000)
}

fun createMenuItem(item: String): MenuItem {
    try {
        val (menu, count) = item.split("-")
        return MenuItem(findMenuItems(menu), count.toInt())
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
    return menuItems
}

fun findMenuItems(menuItems: String): String {
    val menu = Menu.entries.find { it.name == menuItems }
    require(menu != null) { "$ERROR_MESSAGE $INVALID_ORDER_MESSAGE" }
    return menu.toString()
}
