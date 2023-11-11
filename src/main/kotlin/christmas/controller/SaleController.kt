package christmas.controller

import christmas.model.Date
import christmas.model.MenuItem

class SaleController {
    fun saleRun(dateNumber: Int, menuItems: List<MenuItem>) {
        saleOfChristmas(dateNumber)
        saleOFWeekendOrWeekday(dateNumber, menuItems)

    }
    private fun saleOFWeekendOrWeekday(dateNumber: Int, menuItems: List<MenuItem>) {
        val date = Date()
        val day = date.calculateDay(dateNumber)

        when(day) {
            0,1,2,3,4 -> saleOfWeekday(menuItems)
            5, 6 -> saleOfWeekend(menuItems)
        }
    }

    private fun saleOfChristmas(dateNumber: Int): Int {
        val christmasSale = 1000
        var cumulativeAmount = 0
        if(dateNumber <= 25) {
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
}