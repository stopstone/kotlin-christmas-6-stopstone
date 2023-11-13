package christmas.model

class Date(private val dateNumber: String) {

    fun calculateDay(dateNumber: Int): Int {
        val baseDayOfWeek = 5

        val totalDays = dateNumber - 1
        return (baseDayOfWeek + totalDays) % 7
    }


    fun getDate(): Int {
        return dateNumber.toInt()
    }
}