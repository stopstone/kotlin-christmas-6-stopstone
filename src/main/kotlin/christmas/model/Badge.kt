package christmas.model

import christmas.utils.Constants.NOTHING
import christmas.utils.Constants.BADGE_SANTA
import christmas.utils.Constants.BADGE_STAR
import christmas.utils.Constants.BADGE_TREE
import christmas.utils.Constants.NOTHING_DISCOUNT
import christmas.utils.Constants.SANTA_DISCOUNT
import christmas.utils.Constants.STAR_DISCOUNT
import christmas.utils.Constants.TREE_DISCOUNT

enum class Badge(val threshold: Int, val displayBadge: String) {
    SANTA(SANTA_DISCOUNT, BADGE_SANTA),
    TREE(TREE_DISCOUNT, BADGE_TREE),
    STAR(STAR_DISCOUNT, BADGE_STAR),
    NONE(NOTHING_DISCOUNT, NOTHING),
}

