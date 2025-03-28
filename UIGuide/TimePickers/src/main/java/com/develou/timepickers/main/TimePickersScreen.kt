package com.develou.timepickers.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develou.timepickers.main.components.EndTimePicker
import com.develou.timepickers.main.components.StartTimePicker
import com.develou.timepickers.main.components.TimePickersContent
import com.develou.timepickers.main.viewmodel.TimePickersViewModel
import com.develou.timepickers.ui.theme.TimePickersTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickersScreen(
    viewModel: TimePickersViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var startPickerIsVisible by remember { mutableStateOf(false) }
    var endPickerIsVisible by remember { mutableStateOf(false) }

    TimePickersContent(
        state = state,
        onStartClick = { startPickerIsVisible = true },
        onEndClick = { endPickerIsVisible = true }
    )

    StartTimePicker(
        isVisible = startPickerIsVisible,
        state = state,
        onTimeSelected = viewModel::updateStartTime,
        onDismiss = { startPickerIsVisible = false }
    )

    EndTimePicker(
        isVisible = endPickerIsVisible,
        state = state,
        onTimeSelected = viewModel::updateEndTime,
        onDismiss = { endPickerIsVisible = false }
    )
}

@Preview
@Composable
private fun Preview() {
    TimePickersTheme {
        TimePickersScreen()
    }
}