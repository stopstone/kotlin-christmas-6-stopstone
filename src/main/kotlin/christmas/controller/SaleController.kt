package christmas.controller

import christmas.model.Date
import christmas.model.MenuItem

class SaleController {
    fun menuPriceCal(menuItems: List<MenuItem>) {
        println(menuItems.map { it.menu.itemPrice * it.count })
    }

    fun saleOFWeekendOrWeekday(dateNumber: Int, menuItems: List<MenuItem>) {
        val date = Date()
        val day = date.calculateDay(dateNumber)

        println(day)
        if(day == 5 || day == 6) {
            saleOfWeekend(menuItems)
        }
    }

    private fun saleOfWeekend(menuItems: List<MenuItem>) {
        val mainSale = menuItems.filter { it.menu.itemCategory == "메인" }
        return mainSale.forEach { it.count * 2023 }
    }
}