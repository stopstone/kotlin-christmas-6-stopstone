package christmas.model

class Date() {

    fun calculateDay(dateNumber: Int): Int {
        val baseDayOfWeek = 5

        val totalDays = dateNumber - 1
        return (baseDayOfWeek + totalDays) % 7
    }
}