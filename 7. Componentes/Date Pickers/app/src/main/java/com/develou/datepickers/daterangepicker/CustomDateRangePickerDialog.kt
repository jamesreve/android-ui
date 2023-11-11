package com.develou.datepickers.daterangepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import java.time.Duration
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDateRangePickerDialog(
    state: DateRangePickerDialogState,
    onConfirmation: (Long?, Long?) -> Unit
) {

    if (state.isPickerHidden) return

    val internalState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = state.startDate,
        initialSelectedEndDateMillis = state.endDate
    )

    val headline by remember { derivedStateOf { internalState.headline } }

    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = internalState.displayMode == DisplayMode.Input),
        onDismissRequest = state.onDismissClick,
        content = {
            Surface(shape = MaterialTheme.shapes.large) {
                Column(
                    modifier = Modifier.dateRangeDialogModifier(internalState),
                    verticalArrangement = Arrangement.Top
                ) {
                    ModalButtons(state, internalState, onConfirmation)
                    DateRangePicker(
                        state = internalState,
                        headline = {
                            Text(headline, modifier = Modifier.padding(start = 64.dp))
                        }
                    )
                    InputButtons(state, internalState, onConfirmation)
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Modifier.dateRangeDialogModifier(internalState: DateRangePickerState): Modifier {
    val result = if (internalState.displayMode == DisplayMode.Picker)
        Modifier.fillMaxSize()
    else
        Modifier.wrapContentSize()
    return this.then(result.padding(16.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ColumnScope.InputButtons(
    state: DateRangePickerDialogState,
    pickerState: DateRangePickerState,
    onRangeConfirmation: (Long?, Long?) -> Unit
) {
    if (pickerState.displayMode == DisplayMode.Picker) return

    Row(modifier = Modifier.align(Alignment.End)) {
        TextButton(onClick = state.onDismissClick) {
            Text(text = "Cancelar")
        }

        TextButton(
            onClick = {
                state.onConfirmationClick(
                    pickerState.selectedStartDateMillis,
                    pickerState.selectedEndDateMillis
                )
                onRangeConfirmation(
                    pickerState.selectedStartDateMillis,
                    pickerState.selectedEndDateMillis
                )
            },
            enabled = pickerState.selectedEndDateMillis != null
        ) {
            Text(text = "Guardar")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ModalButtons(
    state: DateRangePickerDialogState,
    pickerState: DateRangePickerState,
    onRangeConfirmation: (Long?, Long?) -> Unit
) {
    if (pickerState.displayMode == DisplayMode.Input) return

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = state.onDismissClick) {
            Icon(Icons.Filled.Close, contentDescription = "Cerrar")
        }
        TextButton(
            onClick = {
                state.onConfirmationClick(
                    pickerState.selectedStartDateMillis,
                    pickerState.selectedEndDateMillis
                )
                onRangeConfirmation(
                    pickerState.selectedStartDateMillis,
                    pickerState.selectedEndDateMillis
                )
            },
            enabled = pickerState.selectedEndDateMillis != null
        ) {
            Text(text = "Guardar")
        }
    }
}

@Preview(device = "spec:width=411dp,height=891dp")
@Composable
private fun Preview() {
    val now = Instant.now()
    val nowAfterTenDays = now + Duration.ofDays(10)
    CustomDateRangePickerDialog(
        state = DateRangePickerDialogState(
            startDate = now.toEpochMilli(),
            endDate = nowAfterTenDays.toEpochMilli(),
            isPickerVisible = true,
            onStart = {},
            onConfirmationClick = { _, _ -> },
            onDismissClick = {}
        ),
        onConfirmation = { _, _ -> })
}