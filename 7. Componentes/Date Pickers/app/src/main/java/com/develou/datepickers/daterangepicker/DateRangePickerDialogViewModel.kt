package com.develou.datepickers.daterangepicker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DateRangePickerDialogViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        DateRangePickerDialogState(
            startDate = null,
            endDate = null,
            isPickerVisible = false,
            onStart = ::onStart,
            onConfirmationClick = ::onRangeConfirmationClick,
            onDismissClick = ::onDismissClick
        )
    )

    val state = _state.asStateFlow()

    private fun onStart() {
        _state.update { it.copy(isPickerVisible = true) }
    }

    private fun onRangeConfirmationClick(startDate: Long?, endDate: Long?) {
        _state.update {
            it.copy(startDate = startDate, endDate = endDate, isPickerVisible = false)
        }
    }

    private fun onDismissClick() {
        _state.update { it.copy(isPickerVisible = false) }
    }
}