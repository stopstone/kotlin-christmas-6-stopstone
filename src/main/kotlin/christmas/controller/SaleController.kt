package christmas.controller

import christmas.model.*

class SaleController {
    fun saleRun(dateNumber: Int, menuItems: List<MenuItem>) {
        saleOfChristmas(dateNumber)
        saleOFWeekendOrWeekday(dateNumber, menuItems)
        saleOfSpecial(dateNumber, menuItems)
        presentEvent(menuItems.toMutableList())

    }

    private fun saleOFWeekendOrWeekday(dateNumber: Int, menuItems: List<MenuItem>) {
        val date = Date()
        val day = date.calculateDay(dateNumber)

        when (day) {
            0, 1, 2, 3, 4 -> saleOfWeekday(menuItems)
            5, 6 -> saleOfWeekend(menuItems)
        }
    }

    private fun saleOfChristmas(dateNumber: Int): Int {
        val christmasSale = 1000
        var cumulativeAmount = 0
        if (dateNumber <= 25) {
            cumulativeAmount = dateNumber * 100
        }
        return christmasSale + cumulativeAmount
    }

    private fun saleOfWeekend(menuItems: List<MenuItem>) {
        val mainSale = menuItems.filter { it.menu.itemCategory == "메인" }
        return mainSale.forEach { it.count * 2023 }
    }

    private fun saleOfWeekday(menuItems: List<MenuItem>) {
        val dessertSale = menuItems.filter { it.menu.itemCategory == "디저트" }
        return dessertSale.forEach { it.count * 2023 }
    }

    private fun saleOfSpecial(dateNumber: Int, menuItems: List<MenuItem>): Int {
        val starDays = listOf(3, 10, 17, 24, 25, 31)
        val totalAmount = menuItems.sumOf { it.menu.itemPrice * it.count }

        if (dateNumber in starDays) {
            return (totalAmount - 1_000)
        }
        return totalAmount
    }

    private fun presentEvent(menuItems: MutableList<MenuItem>) {
        val totalAmount = menuItems.sumOf { it.menu.itemPrice * it.count }
        val remainingAmount = totalAmount % 120000
        saleOfPresent(remainingAmount)

        if (totalAmount >= 120000) {
            val gift = MenuItem(Menu.DRINK_CHAMPAGNE, remainingAmount)
            menuItems.add(gift)

            println(menuItems)
        }
    }

    private fun saleOfPresent(remainingAmount: Int): Int {
        return remainingAmount * 120000
    }
}