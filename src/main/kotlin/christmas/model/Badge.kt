package christmas.model

class Badge {
    fun selectBadge(totalAmount: Int): String {
        var badge = "없음"
        if (5000 <= totalAmount) {
            badge = "별"
        }
        if (10000 <= totalAmount) {
            badge = "트리"
        }
        if (20000 <= totalAmount) {
            badge = "산타"
        }
        return badge
    }
}