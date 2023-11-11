package christmas.controller

import christmas.model.Date
import christmas.model.MenuItem

class SaleController {
    fun saleOFWeekendOrWeekday(dateNumber: Int, menuItems: List<MenuItem>) {
        val date = Date()
        val day = date.calculateDay(dateNumber)

        when(day) {
            0,1,2,3,4 -> saleOfWeekday(menuItems)
            5, 6 -> saleOfWeekend(menuItems)
        }
    }

    private fun saleOfWeekend(menuItems: List<MenuItem>) {
        val mainSale = menuItems.filter { it.menu.itemCategory == "메인" }
        return mainSale.forEach { it.count * 2023 }
    }

    private fun saleOfWeekday(menuItems: List<MenuItem>) {
        val mainSale = menuItems.filter { it.menu.itemCategory == "디저트" }
        return mainSale.forEach { it.count * 2023 }
    }
}