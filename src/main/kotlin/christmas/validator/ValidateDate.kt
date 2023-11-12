package christmas.validator

import christmas.utils.Constants.MAX_DATE
import christmas.utils.Constants.MIN_DATE
import christmas.utils.Messages
import christmas.utils.Messages.ERROR_MESSAGE
import christmas.utils.Messages.INVALID_DATE_MESSAGE

object ValidateDate {
    internal fun validate(input: String?): Int {
        require(!input.isNullOrEmpty() && input.all { it.isDigit() }) {"$ERROR_MESSAGE $INVALID_DATE_MESSAGE"}
        require(input.toInt() in MIN_DATE..MAX_DATE) {"$ERROR_MESSAGE $INVALID_DATE_MESSAGE"}
        return input.toInt()
    }
}