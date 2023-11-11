package com.develou.datepickers.datepicker

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomDatePickerDialog(
    state: DatePickerDialogState,
    onDateSelected: (Long?) -> Unit
) {
    if (state.isPickerHidden) return

    val internalState = rememberDatePickerState(
        initialSelectedDateMillis = state.date
    )

    DatePickerDialog(
        onDismissRequest = state.onDismissClick,
        confirmButton = {
            TextButton(
                onClick = {
                    state.onConfirmationClick(internalState.selectedDateMillis)
                    onDateSelected(internalState.selectedDateMillis)
                },
                enabled = internalState.selectedDateMillis != null
            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = state.onDismissClick) {
                Text("Cancelar")
            }
        },
        content = { DatePicker(state = internalState) },
    )
}

@Preview(
    device = "spec:width=411dp,height=891dp,isRound=true", showBackground = true,
    backgroundColor = 0xFF00BCD4, wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE
)
@Composable
private fun Preview() {
    CustomDatePickerDialog(state = DatePickerDialogState(
        date = null,
        isPickerVisible = true,
        onStart = {},
        onConfirmationClick = {},
        onDismissClick = {},
    ), onDateSelected = {})
}