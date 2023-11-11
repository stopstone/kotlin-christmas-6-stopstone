package christmas.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun inputDate(): String {
        val dateNumber = Console.readLine()
        return dateNumber
    }
}
