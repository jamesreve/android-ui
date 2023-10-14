package com.develou.bottomsheets.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _state = MutableStateFlow(MainState.Default)
    val state = _state.asStateFlow()

    fun updateSwipe(enable: Boolean) {
        _state.update { it.copy(swipeGestureEnabled = enable) }
    }

    fun updateModalVisibility(show: Boolean) {
        _state.update { it.copy(isModalSheetVisible = show) }
    }

    fun updateExpandedHeight(value: String) {
        _state.update { it.copy(sheetExpandedHeightPercentage = value.toInt()) }
    }

    fun updatePartiallyHeight(value: String) {
        _state.update { it.copy(sheetPartiallyExpandedHeight = value.toInt()) }
    }

    fun updateLastModalAction(value: String) {
        _state.update { it.copy(lastModalSheetAction = value) }
    }
}