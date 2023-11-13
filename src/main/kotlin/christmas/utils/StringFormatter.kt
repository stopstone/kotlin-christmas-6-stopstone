package christmas.utils

object StringFormatter {
    fun formatNumberWithComma(number: Int): String {
        return "%,d".format(number)
    }
}