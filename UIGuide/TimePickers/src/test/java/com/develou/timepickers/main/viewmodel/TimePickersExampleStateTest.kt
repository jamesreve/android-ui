package com.develou.timepickers.main.viewmodel

import org.junit.Assert.assertEquals
import org.junit.Test


class TimePickersExampleStateTest {

    @Test
    fun shouldSubtractLowerFromHigher() {
        // 07:00am - 9:00am = 2h
        val state = TimePickersState(
            startTimeHour = 7,
            startTimeMinute = 0,
            endTimeHour = 9,
            endTimeMinute = 0
        )
        assertEquals("2h", state.duration())
    }

    @Test
    fun shouldSubtractHigherFromLower() {
        // 10:00am - 5:00am = 19h
        val state = TimePickersState(
            startTimeHour = 10,
            startTimeMinute = 0,
            endTimeHour = 5,
            endTimeMinute = 0
        )
        assertEquals("19h", state.duration())
    }

}