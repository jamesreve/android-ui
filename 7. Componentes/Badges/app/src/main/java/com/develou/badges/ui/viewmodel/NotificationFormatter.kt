package com.develou.badges.ui.viewmodel

fun Int.notificationFormat(maximum: Int): String {
    restrain(this, maximum)
    return when (maximum) {
        1 -> truncate(this, 9)
        2 -> truncate(this, 99)
        3 -> truncate(this, 999)
        else -> error("Imposible")
    }
}

private fun restrain(value: Int, maximum: Int) {
    require(value > 0)
    require(maximum in 1..3)
}

private fun truncate(value: Int, limit: Int): String {
    val maximumIndicator = "+"

    return if (value > limit)
        "$limit" + (maximumIndicator)
    else
        value.toString()
}
