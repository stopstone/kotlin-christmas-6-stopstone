package christmas.model

import christmas.utils.Constants.CHRISTMAS_SALE_NAME
import christmas.utils.Constants.PRESENT_EVENT_NAME
import christmas.utils.Constants.SPECIAL_SALE_NAME
import christmas.utils.Constants.WEEKDAY_SALE_NAME
import christmas.utils.Constants.WEEKEND_SALE_NAME

enum class Sale(val saleName: String) {
    CHRISTMAS_SALE(CHRISTMAS_SALE_NAME),
    WEEKDAY_SALE(WEEKDAY_SALE_NAME),
    WEEKEND_SALE(WEEKEND_SALE_NAME),
    SPECIAL_SALE(SPECIAL_SALE_NAME),
    PRESENT_EVENT(PRESENT_EVENT_NAME),
}