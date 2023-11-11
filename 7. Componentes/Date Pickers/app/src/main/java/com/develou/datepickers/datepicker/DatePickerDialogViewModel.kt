package com.develou.datepickers.datepicker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DatePickerDialogViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        DatePickerDialogState(
            date = null,
            isPickerVisible = false,
            onStart = ::onStart,
            onDismissClick = ::onDismissClick,
            onConfirmationClick = ::onConfirmationClick
        )
    )

    val state = _state.asStateFlow()

    private fun onStart() {
        _state.update { it.copy(isPickerVisible = true) }
    }

    private fun onDismissClick() {
        _state.update { it.copy(isPickerVisible = false) }
    }

    private fun onConfirmationClick(date: Long?) {
        _state.update { it.copy(isPickerVisible = false, date = date) }
    }
}