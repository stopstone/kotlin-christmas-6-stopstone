package christmas.controller

import christmas.model.*

class SaleController {
    private var saleItems = mutableListOf<Int>()
    fun saleStart(dateNumber: Int, menuItems: List<MenuItem>): MutableList<Int> {
        saleOfChristmas(dateNumber)
        saleOfWeekendOrWeekday(dateNumber, menuItems)
        saleOfSpecial(dateNumber)
        presentEvent(menuItems.toMutableList())

        return saleItems
    }
    fun totalOrderAmount(menuItems: List<MenuItem>): Int {
        return menuItems.sumOf { it.menu.itemPrice * it.count }
    }

    fun totalDiscountAmount(totalDiscount: MutableList<Int>): Int {
        return totalDiscount.sum()
    }

    private fun saleOfChristmas(dateNumber: Int) {
        val christmasSale = 1000
        val cumulativeAmount: Int

        if (dateNumber <= 25) {
            cumulativeAmount = dateNumber * 100
            saleItems.add((christmasSale + cumulativeAmount) - 100)
        }
    }

    private fun saleOfWeekendOrWeekday(dateNumber: Int, menuItems: List<MenuItem>) {
        val date = Date()
        var day = date.calculateDay(dateNumber)

        when (day) {
            0, 1, 2, 3, 4 -> {
                saleOfWeekday(menuItems)
                saleItems.add(0)
            }
            5, 6 -> {
                saleItems.add(0)
                saleOfWeekend(menuItems)
            }
        }
    }


    private fun saleOfWeekend(menuItems: List<MenuItem>) {
        val mainSale = menuItems.filter { it.menu.itemCategory == "메인" }
        val resultMainSale = mainSale.map { it.count * 2023 }

        if (resultMainSale.isNotEmpty()) {
            saleItems.add(resultMainSale.first())
        }
    }

    private fun saleOfWeekday(menuItems: List<MenuItem>) {
        val dessertSale = menuItems.filter { it.menu.itemCategory == "디저트" }
        val resultDessertSale = dessertSale.map { it.count * 2023 }

        if (resultDessertSale.isNotEmpty()) {
            saleItems.add(resultDessertSale.first())
        }
    }

    private fun saleOfSpecial(dateNumber: Int) {
        val starDays = listOf(3, 10, 17, 24, 25, 31)

        if (dateNumber in starDays) {
            saleItems.add(1000)
        }
    }

    private fun presentEvent(menuItems: MutableList<MenuItem>) {
        val totalAmount = menuItems.sumOf { it.menu.itemPrice * it.count }
        val remainingAmount = totalAmount / 120000
        saleOfPresent(remainingAmount)
    }

    private fun saleOfPresent(remainingAmount: Int) {
        saleItems.add(remainingAmount * 25000)
    }
}