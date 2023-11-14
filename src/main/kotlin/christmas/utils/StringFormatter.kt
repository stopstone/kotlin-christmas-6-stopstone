package christmas.utils

import christmas.utils.Constants.WON

object StringFormatter {
    fun formatNumberWithComma(number: Int): String {
        return "%,d${WON}".format(number)
    }
}