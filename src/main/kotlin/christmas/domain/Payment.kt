package christmas.domain

import christmas.model.*
import christmas.repository.MenuItem
import christmas.repository.SaleItem
import christmas.utils.Constants.CHRISTMAS_DISCOUNT
import christmas.utils.Constants.DESSERT
import christmas.utils.Constants.DISCOUNT_PLUS
import christmas.utils.Constants.DISCOUNT_START
import christmas.utils.Constants.FRI
import christmas.utils.Constants.MAIN
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

class Payment(private val date: Date) {
    private val saleItems = mutableListOf<SaleItem>()
    fun saleStart(menuItems: List<MenuItem>): MutableList<SaleItem> {
        saleOfChristmas()
        saleOfWeekendOrWeekday(menuItems)
        saleOfSpecial()
        saleOfPresent(menuItems)

        return saleItems
    }

    fun totalDiscountAmount(): Int {
        return saleItems.sumOf { it.discountAmount }
    }

    private fun saleOfChristmas() {
        val dateNumber = date.getDate()
        val cumulativeAmount: Int

        when {
            dateNumber <= CHRISTMAS_DISCOUNT -> {
                cumulativeAmount = dateNumber * DISCOUNT_PLUS
                saleItems.add(SaleItem(Sale.CHRISTMAS_SALE, DISCOUNT_START + cumulativeAmount))
            }
            dateNumber > CHRISTMAS_DISCOUNT -> {
                saleItems.add(SaleItem(Sale.CHRISTMAS_SALE, NO_DISCOUNT))
            }
        }
    }

    private fun saleOfWeekendOrWeekday(menuItems: List<MenuItem>) {
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
        val resultMainSale = menuItems.filter { it.menu.itemCategory == MAIN }
            .map { it.count * WEEK_DISCOUNT }
            .firstOrNull()
        resultMainSale?.let {
            saleItems.add(SaleItem(Sale.WEEKEND_SALE, it))
        }
    }

    private fun saleOfWeekday(menuItems: List<MenuItem>) {
        val resultDessertSale = menuItems.filter { it.menu.itemCategory == DESSERT }
            .map { it.count * WEEK_DISCOUNT }
            .firstOrNull()
        resultDessertSale?.let {
            saleItems.add(SaleItem(Sale.WEEKDAY_SALE, it))
        }
    }

    private fun isSpecialDay(dateNumber: Int): Boolean {
        val starDays = listOf(3, 10, 17, 24, 25, 31)
        return dateNumber in starDays
    }

    private fun saleOfSpecial() {
        val dateNumber = date.getDate()

        if (isSpecialDay(dateNumber)) {
            saleItems.add(SaleItem(Sale.SPECIAL_SALE, SPECIAL_DISCOUNT))
        }
    }

    private fun saleOfPresent(menuItems: List<MenuItem>) {
        val totalAmount = TotalAmount(menuItems)
        val champagne = totalAmount.getTotalAmount() / PRESENT_AMOUNT
        saleItems.add(SaleItem(Sale.PRESENT_EVENT, champagne * PRESENT_DISCOUNT))
    }
}