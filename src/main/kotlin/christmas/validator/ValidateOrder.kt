package christmas.validator

import christmas.utils.Messages

object ValidateOrder {
    internal fun checkDuplicateMenu(input: String) {
        val menuSet = mutableSetOf<String>()

        input.split(",").forEach {
            val menu = it.split("-").first()
            if (!menuSet.add(menu)) {
                throw IllegalArgumentException("${Messages.ERROR_MESSAGE} ${Messages.INVALID_ORDER_MESSAGE}")
            }
        }
    }
}