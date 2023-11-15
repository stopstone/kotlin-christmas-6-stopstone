package christmas.domain

import christmas.utils.Constants.BASE_DAY
import christmas.utils.Constants.WEEK
import christmas.validator.ValidateNumber.requireValidDateNumber

class Date(private val dateNumber: String) {
    init {
        requireValidDateNumber(dateNumber)
    }

    fun calculateDay(dateNumber: Int): Int {
        val baseDayOfWeek = BASE_DAY
        val totalDays = dateNumber - 1

        return (baseDayOfWeek + totalDays) % WEEK
    }

    fun getDate(): Int {
        return dateNumber.toInt()
    }
}