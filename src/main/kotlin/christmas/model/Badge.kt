package christmas.model

import christmas.utils.Constants.BADGE_SANTA
import christmas.utils.Constants.BADGE_STAR
import christmas.utils.Constants.BADGE_TREE
import christmas.utils.Constants.NOTHING
import christmas.utils.Constants.SANTA_DISCOUNT
import christmas.utils.Constants.STAR_DISCOUNT
import christmas.utils.Constants.TREE_DISCOUNT

class Badge {
    fun selectBadge(totalAmount: Int): String {
        var badge = NOTHING
        if (STAR_DISCOUNT <= totalAmount) {
            badge = BADGE_STAR
        }
        if (TREE_DISCOUNT <= totalAmount) {
            badge = BADGE_TREE
        }
        if (SANTA_DISCOUNT <= totalAmount) {
            badge = BADGE_SANTA
        }
        return badge
    }
}