package christmas.validator

import christmas.utils.Messages

object ValidateDate {
    internal fun validate(input: String?): Int {
        require(!input.isNullOrEmpty() && input.all { it.isDigit() }) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_MESSAGE}"}
        require(input.toInt() in 1..31) {"${Messages.ERROR_MESSAGE} ${Messages.INVALID_MESSAGE}"}
        return input.toInt()
    }
}