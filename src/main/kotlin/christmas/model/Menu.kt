package christmas.model

import christmas.utils.Constants

enum class Menu(val itemCategory: String, val itemName: String, val itemPrice: Int) {
    APPETIZER_MUSHROOM_SOUP(Constants.APPETIZER, Constants.MUSHROOM_SOUP, Constants.MUSHROOM_SOUP_COST),
    APPETIZER_TAPAS(Constants.APPETIZER, Constants.TAPAS, Constants.TAPAS_COST),
    APPETIZER_CAESAR_SALAD(Constants.APPETIZER, Constants.CAESAR_SALAD, Constants.CAESAR_SALAD_COST),

    MAIN_T_BONE_STEAK(Constants.MAIN, Constants.T_BORN, Constants.T_BORN_COST),
    MAIN_BBQ_RIB(Constants.MAIN, Constants.BBQ, Constants.BBQ_COST),
    MAIN_SEAFOOD_PASTA(Constants.MAIN, Constants.SEAFOOD_PASTA, Constants.SEAFOOD_PASTA_COST),
    MAIN_CHRISTMAS_PASTA(Constants.MAIN, Constants.CHRISTMAS_PASTA, Constants.CHRISTMAS_PASTA_COST),

    DESSERT_CHOCOLATE_CAKE(Constants.DESSERT, Constants.CHOCO_CAKE, Constants.CHOCO_CAKE_COST),
    DESSERT_ICE_CREAM(Constants.DESSERT, Constants.ICE_CREAM, Constants.ICE_CREAM_COST),

    DRINK_ZERO_COLA(Constants.DRINK, Constants.ZERO_COKE, Constants.ZERO_COKE_COST),
    DRINK_RED_WINE(Constants.DRINK, Constants.RED_WINE, Constants.RED_WINE_COST),
    DRINK_CHAMPAGNE(Constants.DRINK, Constants.CHAMPAGNE, Constants.CHAMPAGNE_COST)
}