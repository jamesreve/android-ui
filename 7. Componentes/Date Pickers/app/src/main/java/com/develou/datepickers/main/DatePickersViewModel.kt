package com.develou.datepickers.main

import androidx.lifecycle.ViewModel
import com.develou.datepickers.util.formatDate
import com.develou.datepickers.util.formatRange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DatePickersViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        DatePickersState(
            dateSelected = "Seleccionar fecha",
            rangeSelected = "Seleccionar rango",
            onDateSelected = ::onDateConfirmation,
            onRangeSelected = ::onRangeConfirmation
        )
    )

    val state = _state.asStateFlow()

    fun onDateConfirmation(date: Long?) {
        _state.update { it.copy(dateSelected = formatDate(date)) }
    }

    fun onRangeConfirmation(startDate: Long?, endDate: Long?) {
        _state.update { it.copy(rangeSelected = formatRange(startDate, endDate)) }
    }
}