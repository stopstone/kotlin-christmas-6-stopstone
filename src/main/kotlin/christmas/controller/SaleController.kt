package christmas.controller

import christmas.model.*

class SaleController {

    fun saleStart(dateNumber: Int, menuItems: List<MenuItem>): List<Int> {
        val weekDiscount = saleOFWeekendOrWeekday(dateNumber, menuItems)
        val christmasDiscount = saleOfChristmas(dateNumber)
        val specialDiscount = saleOfSpecial(dateNumber, menuItems)
        val presentDiscount = presentEvent(menuItems.toMutableList())

        return listOf(weekDiscount, christmasDiscount, specialDiscount, presentDiscount)
    }
    fun totalOrderAmount(menuItems: List<MenuItem>): Int {
        return menuItems.sumOf { it.menu.itemPrice * it.count }
    }

    fun totalDiscountAmount(totalDiscount: List<Int>): Int {
        return totalDiscount.sum()
    }

    private fun saleOFWeekendOrWeekday(dateNumber: Int, menuItems: List<MenuItem>): Int {
        val date = Date()
        var day = date.calculateDay(dateNumber)

        when (day) {
            0, 1, 2, 3, 4 -> day = saleOfWeekday(menuItems)
            5, 6 -> day = saleOfWeekend(menuItems)
        }
        return day
    }

    private fun saleOfChristmas(dateNumber: Int): Int {
        val christmasSale = 1000
        var cumulativeAmount = 0
        if (dateNumber <= 25) {
            cumulativeAmount = dateNumber * 100
        }
        return christmasSale + cumulativeAmount
    }

    private fun saleOfWeekend(menuItems: List<MenuItem>): Int {
        val mainSale = menuItems.filter { it.menu.itemCategory == "메인" }
        val resultMainSale = mainSale.map { it.count * 2023 }

        if (resultMainSale.isNotEmpty()) {
            return resultMainSale.first()
        }
        return 0
    }

    private fun saleOfWeekday(menuItems: List<MenuItem>): Int {
        val dessertSale = menuItems.filter { it.menu.itemCategory == "디저트" }
        val resultDessertSale = dessertSale.map { it.count * 2023 }

        if (resultDessertSale.isNotEmpty()) {
            return resultDessertSale.first()
        }
        return 0
    }

    private fun saleOfSpecial(dateNumber: Int, menuItems: List<MenuItem>): Int {
        val starDays = listOf(3, 10, 17, 24, 25, 31)
        val totalAmount = menuItems.sumOf { it.menu.itemPrice * it.count }

        if (dateNumber in starDays) {
            return (totalAmount - 1_000)
        }
        return totalAmount
    }

    private fun presentEvent(menuItems: MutableList<MenuItem>): Int {
        val totalAmount = menuItems.sumOf { it.menu.itemPrice * it.count }
        val remainingAmount = totalAmount % 120000
        saleOfPresent(remainingAmount)

        if (totalAmount >= 120000) {
            val gift = MenuItem(Menu.DRINK_CHAMPAGNE, remainingAmount)
            menuItems.add(gift)
        }
        return saleOfPresent(remainingAmount)
    }

    private fun saleOfPresent(remainingAmount: Int): Int {
        return remainingAmount * 120000
    }
}