package com.develou.bottomsheets.ui.components.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

data class ExposedDropdownMenuState(
    val label: String,
    val options: List<String>,
    val optionSelected: String,
    val onOptionSelection: (String) -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExposedDropdownMenu(state: ExposedDropdownMenuState) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(

        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = state.optionSelected,
            onValueChange = { },
            label = { Text(state.label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            state.options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        state.onOptionSelection(option)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ExposedDropdownMenu(
        ExposedDropdownMenuState(
            label = "Menu",
            options = listOf("Opción 1", "Opción 2", "Opción 3"),
            onOptionSelection = {},
            optionSelected = "Opción 1"
        )
    )
}