package christmas.controller

import christmas.repository.MenuItem
import christmas.utils.Constants.APPETIZER
import christmas.utils.Constants.BBQ
import christmas.utils.Constants.BBQ_COST
import christmas.utils.Constants.CAESAR_SALAD
import christmas.utils.Constants.CAESAR_SALAD_COST
import christmas.utils.Constants.CHAMPAGNE
import christmas.utils.Constants.CHAMPAGNE_COST
import christmas.utils.Constants.CHOCO_CAKE
import christmas.utils.Constants.CHOCO_CAKE_COST
import christmas.utils.Constants.CHRISTMAS_PASTA
import christmas.utils.Constants.CHRISTMAS_PASTA_COST
import christmas.utils.Constants.DESSERT
import christmas.utils.Constants.DRINK
import christmas.utils.Constants.ICE_CREAM
import christmas.utils.Constants.ICE_CREAM_COST
import christmas.utils.Constants.ITEMS_DELIMITER
import christmas.utils.Constants.MAIN
import christmas.utils.Constants.MUSHROOM_SOUP
import christmas.utils.Constants.MUSHROOM_SOUP_COST
import christmas.utils.Constants.RED_WINE
import christmas.utils.Constants.RED_WINE_COST
import christmas.utils.Constants.SEAFOOD_PASTA
import christmas.utils.Constants.SEAFOOD_PASTA_COST
import christmas.utils.Constants.TAPAS
import christmas.utils.Constants.TAPAS_COST
import christmas.utils.Constants.T_BORN
import christmas.utils.Constants.T_BORN_COST
import christmas.utils.Constants.ZERO_COKE
import christmas.utils.Constants.ZERO_COKE_COST
import christmas.utils.ErrorMessage.ERROR_MESSAGE
import christmas.utils.ErrorMessage.INVALID_ORDER_MESSAGE
import christmas.validator.ValidateOrder.checkCountZeroMenu
import christmas.validator.UserCaution.checkLimitOrderMenu
import christmas.validator.ValidateOrder.checkMinOrderMenu
import christmas.validator.UserCaution.checkOrderOnlyDrink

enum class Menu(val itemCategory: String, val itemName: String, val itemPrice: Int) {
    APPETIZER_MUSHROOM_SOUP(APPETIZER, MUSHROOM_SOUP, MUSHROOM_SOUP_COST),
    APPETIZER_TAPAS(APPETIZER, TAPAS, TAPAS_COST),
    APPETIZER_CAESAR_SALAD(APPETIZER, CAESAR_SALAD, CAESAR_SALAD_COST),

    MAIN_T_BONE_STEAK(MAIN, T_BORN, T_BORN_COST),
    MAIN_BBQ_RIB(MAIN, BBQ, BBQ_COST),
    MAIN_SEAFOOD_PASTA(MAIN, SEAFOOD_PASTA, SEAFOOD_PASTA_COST),
    MAIN_CHRISTMAS_PASTA(MAIN, CHRISTMAS_PASTA, CHRISTMAS_PASTA_COST),

    DESSERT_CHOCOLATE_CAKE(DESSERT, CHOCO_CAKE, CHOCO_CAKE_COST),
    DESSERT_ICE_CREAM(DESSERT, ICE_CREAM, ICE_CREAM_COST),

    DRINK_ZERO_COLA(DRINK, ZERO_COKE, ZERO_COKE_COST),
    DRINK_RED_WINE(DRINK, RED_WINE, RED_WINE_COST),
    DRINK_CHAMPAGNE(DRINK, CHAMPAGNE, CHAMPAGNE_COST)
}

fun createMenuItem(item: String): MenuItem {
    try {
        val (menu, count) = item.split(ITEMS_DELIMITER)
        checkCountZeroMenu(count)
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
    checkMinOrderMenu(menuItems)
    checkLimitOrderMenu(menuItems)
    checkOrderOnlyDrink(menuItems)
}

fun findMenuItems(menuName: String): Menu {
    return Menu.entries.find { it.itemName == menuName }
        ?: throw IllegalArgumentException("$ERROR_MESSAGE $INVALID_ORDER_MESSAGE")
}