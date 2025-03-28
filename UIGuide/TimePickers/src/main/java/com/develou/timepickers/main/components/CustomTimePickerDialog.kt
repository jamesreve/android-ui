package com.develou.timepickers.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Keyboard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develou.timepickers.util.currentHour
import com.develou.timepickers.util.currentMinute


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomTimePickerDialog(
    currentHour: Int = currentHour(),
    currentMinute: Int = currentMinute(),
    is24Hour: Boolean = false,
    onTimeSelected: (Int, Int) -> Unit,
    onDismiss: () -> Unit,
) {
    val timePickerState = rememberTimePickerState(
        initialHour = currentHour,
        initialMinute = currentMinute,
        is24Hour = is24Hour
    )
    var isDialType by remember { mutableStateOf(true) }

    TimePickerDialog(
        modifier = Modifier.requiredWidthIn(min = 304.dp),
        onDismissRequest = onDismiss,
        title = {
            Headline()
        },
        modeToggleButton = {
            IconButton(onClick = { isDialType = !isDialType }) {
                Icon(imageVector = toggleIconFor(isDialType), contentDescription = null)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onTimeSelected(timePickerState.hour, timePickerState.minute)
                onDismiss()
            }) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        if (isDialType) {
            TimePicker(state = timePickerState)
        } else {
            TimeInput(state = timePickerState)
        }
    }
}

private fun toggleIconFor(isDialType: Boolean): ImageVector {
    return if (isDialType) {
        Icons.Outlined.Keyboard
    } else {
        Icons.Outlined.AccessTime
    }
}

@Composable
private fun Headline() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Text(
            text = "Selecciona la hora",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Preview
@Composable
private fun Preview() {
    CustomTimePickerDialog(
        currentHour = 12,
        currentMinute = 45,
        onTimeSelected = { _, _ -> },
        onDismiss = { }
    )
}

