package com.develou.timepickers.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

private fun getCurrentDateTime() =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

fun currentHour(): Int = getCurrentDateTime().hour

fun currentMinute(): Int = getCurrentDateTime().minute

fun LocalTime.as12HoursFormat():String{
    return TIME_FORMAT.format(this)
}

private val TIME_FORMAT = LocalTime.Format {
    amPmHour(Padding.NONE)
    char(':')
    minute()
    amPmMarker("am", "pm")
}
