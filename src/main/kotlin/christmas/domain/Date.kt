package christmas.domain

import christmas.utils.Constants
import christmas.utils.Constants.BASE_DAY
import christmas.utils.Constants.WEEK
import christmas.utils.Messages

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

    private fun requireValidDateNumber(dateNumber: String) {
        require(dateNumber.isNotEmpty() && dateNumber.all { it.isDigit() }) {
            "${Messages.ERROR_MESSAGE} ${Messages.INVALID_DATE_MESSAGE}"
        }
        require(dateNumber.toInt() in Constants.MIN_DATE..Constants.MAX_DATE) {
            "${Messages.ERROR_MESSAGE} ${Messages.INVALID_DATE_MESSAGE}"
        }
    }

}