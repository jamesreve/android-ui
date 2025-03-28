package com.develou.timepickers.main.components

import androidx.compose.runtime.Composable
import com.develou.timepickers.main.viewmodel.TimePickersState

@Composable
fun StartTimePicker(
    isVisible: Boolean,
    state: TimePickersState,
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {
    if (isVisible) {
        CustomTimePickerDialog(
            currentHour = state.startTimeHour,
            currentMinute = state.startTimeMinute,
            onTimeSelected = onTimeSelected,
            onDismiss = onDismiss
        )
    }
}