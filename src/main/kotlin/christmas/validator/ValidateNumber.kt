package christmas.validator

import christmas.utils.Constants
import christmas.utils.ErrorMessage

object ValidateNumber {
    internal fun requireValidDateNumber(dateNumber: String) {
        require(dateNumber.isNotEmpty() && dateNumber.all { it.isDigit() }) {
            "${ErrorMessage.ERROR_MESSAGE} ${ErrorMessage.INVALID_DATE_MESSAGE}"
        }
        require(dateNumber.toInt() in Constants.MIN_DATE..Constants.MAX_DATE) {
            "${ErrorMessage.ERROR_MESSAGE} ${ErrorMessage.INVALID_DATE_MESSAGE}"
        }
    }
}