package com.develou.timepickers.main.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.develou.timepickers.main.viewmodel.TimePickersState
import com.develou.timepickers.ui.theme.TimePickersTheme

@Composable
fun EndTimePicker(
    isVisible: Boolean,
    state: TimePickersState,
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit
) {

    if (isVisible) {
        CustomTimePickerDialog(
            currentHour = state.endTimeHour,
            currentMinute = state.startTimeMinute,
            is24Hour = true,
            onTimeSelected = onTimeSelected,
            onDismiss = onDismiss
        )
    }
}

@Preview
@Composable
private fun Preview() {
    TimePickersTheme {
        EndTimePicker(
            isVisible = true,
            state = TimePickersState(),
            onTimeSelected = { _, _ -> },
            onDismiss = { }
        )
    }
}