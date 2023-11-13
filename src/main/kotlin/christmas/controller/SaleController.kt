package christmas.controller

import christmas.domain.Date
import christmas.domain.TotalAmount
import christmas.model.*
import christmas.utils.Constants.CHRISTMAS_DISCOUNT
import christmas.utils.Constants.DISCOUNT_PLUS
import christmas.utils.Constants.DISCOUNT_START
import christmas.utils.Constants.FRI
import christmas.utils.Constants.MON
import christmas.utils.Constants.NO_DISCOUNT
import christmas.utils.Constants.PRESENT_AMOUNT
import christmas.utils.Constants.PRESENT_DISCOUNT
import christmas.utils.Constants.SAT
import christmas.utils.Constants.SPECIAL_DISCOUNT
import christmas.utils.Constants.SUN
import christmas.utils.Constants.THU
import christmas.utils.Constants.TUE
import christmas.utils.Constants.WED
import christmas.utils.Constants.WEEK_DISCOUNT

class SaleController {
    private var saleItems = mutableListOf<SaleItem>()
    fun saleStart(dateNumber: Int, menuItems: List<MenuItem>): MutableList<SaleItem> {
        val date = Date(dateNumber.toString())

        saleOfChristmas(date)
        saleOfWeekendOrWeekday(date, menuItems)
        saleOfSpecial(date)
        saleOfPresent(menuItems)

        return saleItems
    }
    fun totalOrderAmount(menuItems: List<MenuItem>): Int {
        return menuItems.sumOf { it.menu.itemPrice * it.count }
    }

    fun totalDiscountAmount(): Int {
        return saleItems.sumOf { it.discountAmount }
    }

    private fun saleOfChristmas(date: Date) {
        var christmasSale = DISCOUNT_START
        val cumulativeAmount: Int
        val dateNumber = date.getDate()

        if (dateNumber <= CHRISTMAS_DISCOUNT) {
            cumulativeAmount = dateNumber * DISCOUNT_PLUS
            christmasSale += cumulativeAmount
        }
        if (dateNumber > CHRISTMAS_DISCOUNT) {
            christmasSale = NO_DISCOUNT
        }
        saleItems.add(SaleItem(Sale.CHRISTMAS_SALE, christmasSale))
    }

    private fun saleOfWeekendOrWeekday(date: Date, menuItems: List<MenuItem>) {
        val dateNumber = date.getDate()
        val day = date.calculateDay(dateNumber)
        when (day) {
            SUN, MON, TUE, WED, THU -> {
                saleOfWeekday(menuItems)
                saleItems.add(SaleItem(Sale.WEEKEND_SALE, NO_DISCOUNT))
            }
            FRI, SAT -> {
                saleItems.add(SaleItem(Sale.WEEKDAY_SALE, NO_DISCOUNT))
                saleOfWeekend(menuItems)
            }
        }
    }


    private fun saleOfWeekend(menuItems: List<MenuItem>) {
        val mainSale = menuItems.filter { it.menu.itemCategory == "메인" }
        val resultMainSale = mainSale.map { it.count * WEEK_DISCOUNT }

        if (resultMainSale.isNotEmpty()) {
            saleItems.add(SaleItem(Sale.WEEKEND_SALE, resultMainSale.first()))
        }
    }

    private fun saleOfWeekday(menuItems: List<MenuItem>) {
        val dessertSale = menuItems.filter { it.menu.itemCategory == "디저트" }
        val resultDessertSale = dessertSale.map { it.count * WEEK_DISCOUNT }

        if (resultDessertSale.isNotEmpty()) {
            saleItems.add(SaleItem(Sale.WEEKDAY_SALE, resultDessertSale.first()))
        }
    }

    private fun saleOfSpecial(date: Date) {
        val starDays = listOf(3, 10, 17, 24, 25, 31)
        val dateNumber = date.getDate()

        if (dateNumber in starDays) {
            saleItems.add(SaleItem(Sale.SPECIAL_SALE, SPECIAL_DISCOUNT))
        }
    }

    private fun saleOfPresent(menuItems: List<MenuItem>) {
        val totalAmount = TotalAmount(menuItems)
        val champagne = totalAmount.getTotalAmount() / PRESENT_AMOUNT
        saleItems.add(SaleItem(Sale.PRESENT_EVENT, champagne * PRESENT_DISCOUNT))
    }
}