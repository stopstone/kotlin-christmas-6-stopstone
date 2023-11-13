package christmas.controller

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
    private var saleItems = mutableListOf<Int>()
    fun saleStart(dateNumber: Int, menuItems: List<MenuItem>): MutableList<Int> {
        val date = Date(dateNumber.toString())

        saleOfChristmas(date)
        saleOfWeekendOrWeekday(date, menuItems)
        saleOfSpecial(date)
        presentEvent(menuItems.toMutableList())

        return saleItems
    }
    fun totalOrderAmount(menuItems: List<MenuItem>): Int {
        return menuItems.sumOf { it.menu.itemPrice * it.count }
    }

    fun totalDiscountAmount(totalDiscount: MutableList<Int>): Int {
        return totalDiscount.sum()
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
        saleItems.add(christmasSale)
    }

    private fun saleOfWeekendOrWeekday(date: Date, menuItems: List<MenuItem>) {
        val dateNumber = date.getDate()
        val day = date.calculateDay(dateNumber)
        when (day) {
            SUN, MON, TUE, WED, THU -> {
                saleOfWeekday(menuItems)
                saleItems.add(NO_DISCOUNT)
            }
            FRI, SAT -> {
                saleItems.add(NO_DISCOUNT)
                saleOfWeekend(menuItems)
            }
        }
    }


    private fun saleOfWeekend(menuItems: List<MenuItem>) {
        val mainSale = menuItems.filter { it.menu.itemCategory == "메인" }
        val resultMainSale = mainSale.map { it.count * WEEK_DISCOUNT }

        if (resultMainSale.isNotEmpty()) {
            saleItems.add(resultMainSale.first())
        }
    }

    private fun saleOfWeekday(menuItems: List<MenuItem>) {
        val dessertSale = menuItems.filter { it.menu.itemCategory == "디저트" }
        val resultDessertSale = dessertSale.map { it.count * WEEK_DISCOUNT }

        if (resultDessertSale.isNotEmpty()) {
            saleItems.add(resultDessertSale.first())
        }
    }

    private fun saleOfSpecial(date: Date) {
        val starDays = listOf(3, 10, 17, 24, 25, 31)
        val dateNumber = date.getDate()

        if (dateNumber in starDays) {
            saleItems.add(SPECIAL_DISCOUNT)
        }
    }

    private fun presentEvent(menuItems: MutableList<MenuItem>) {
        val totalAmount = menuItems.sumOf { it.menu.itemPrice * it.count }
        val remainingAmount = totalAmount / PRESENT_AMOUNT
        saleOfPresent(remainingAmount)
    }

    private fun saleOfPresent(remainingAmount: Int) {
        saleItems.add(remainingAmount * PRESENT_DISCOUNT)
    }
}