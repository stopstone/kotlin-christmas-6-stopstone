package christmas.domain

import christmas.utils.Constants
import christmas.utils.Messages

class Date(private val dateNumber: String) {

    init {
        require(dateNumber.isNotEmpty() && dateNumber.all { it.isDigit() }) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_DATE_MESSAGE}"}
        require(dateNumber.toInt() in Constants.MIN_DATE..Constants.MAX_DATE) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_DATE_MESSAGE}"}
    }
    fun calculateDay(dateNumber: Int): Int {
        val baseDayOfWeek = 5

        val totalDays = dateNumber - 1
        return (baseDayOfWeek + totalDays) % 7
    }


    fun getDate(): Int {
        return dateNumber.toInt()
    }
}