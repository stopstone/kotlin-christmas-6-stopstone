package christmas.domain

import christmas.model.MenuItem

class TotalAmount(private val menuItems: List<MenuItem>) {
    fun getTotalAmount(): Int {
        return menuItems.sumOf { it.menu.itemPrice * it.count }
    }
}