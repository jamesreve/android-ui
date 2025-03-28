package com.develou.timepickers.main.viewmodel

import com.develou.timepickers.util.as12HoursFormat
import kotlinx.datetime.LocalTime
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

data class TimePickersState(
    val startTimeHour: Int = 10,
    val startTimeMinute: Int = 0,
    val endTimeHour: Int = 11,
    val endTimeMinute: Int = 0
) {

    fun startTimeString(): String {
        return LocalTime(startTimeHour, startTimeMinute).as12HoursFormat()
    }

    fun endTimeString(): String {
        return LocalTime(endTimeHour, endTimeMinute).as12HoursFormat()
    }

    fun duration(): String {
        val startDuration = startTimeHour.hours + startTimeMinute.minutes
        val endDuration = endTimeHour.hours + endTimeMinute.minutes

        var duration = (endDuration - startDuration)

        if (duration.isNegative())
            duration += 24.hours

        return duration.toString()
    }
}