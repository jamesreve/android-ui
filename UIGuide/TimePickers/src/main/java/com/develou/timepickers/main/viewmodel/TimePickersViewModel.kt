package com.develou.timepickers.main.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TimePickersViewModel : ViewModel() {

    private val _state = MutableStateFlow(TimePickersState())
    val state = _state.asStateFlow()

    fun updateStartTime(hour: Int, minute: Int) {
        _state.update { currentState ->
            currentState.copy(
                startTimeHour = hour,
                startTimeMinute = minute
            )
        }
    }

    fun updateEndTime(hour: Int, minute: Int) {
        _state.update { currentState ->
            currentState.copy(
                endTimeHour = hour,
                endTimeMinute = minute
            )
        }
    }
}