package com.develou.datepickers.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DatePickersContent(
    padding: PaddingValues,
    state: DatePickersState,
    onDateButtonClick: () -> Unit,
    onRangeButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
    ) {
        Text("Date Picker")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.dateSelected,
            onValueChange = {},
            readOnly = true,
            leadingIcon = {
                IconButton(onClick = onDateButtonClick) {
                    Icon(imageVector = Icons.Filled.CalendarToday, contentDescription = null)
                }
            }
        )

        Spacer(Modifier.size(16.dp))

        Text("Date Picker con Rango")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.rangeSelected,
            onValueChange = {},
            readOnly = true,
            leadingIcon = {
                IconButton(onClick = onRangeButtonClick) {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = null)
                }
            }
        )
    }
}