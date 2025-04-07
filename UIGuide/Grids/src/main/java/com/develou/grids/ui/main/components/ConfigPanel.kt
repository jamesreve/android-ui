@file:OptIn(ExperimentalMaterial3Api::class)

package com.develou.grids.ui.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develou.grids.ui.main.GridType
import com.develou.grids.ui.main.base.RadioGroup
import com.develou.grids.ui.main.base.SliderSetting

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigPanel(
    selectedType: GridType = GridType.Basic,
    selectedOrientation: GridOrientation = GridOrientation.Vertical,
    onTypeSelected: (GridType) -> Unit = {},
    onOrientationSelected: (GridOrientation) -> Unit = {},
    contentPadding: Float = 0f,
    onContentPaddingChange: (Float) -> Unit = {},
    verticalSpacing: Float = 0f,
    onVerticalSpacingChange: (Float) -> Unit = {},
    horizontalSpacing: Float = 0f,
    onHorizontalSpacingChange: (Float) -> Unit = {},
    cellSize: Float = 0f,
    onCellSizeChange: (Float) -> Unit = {},
    cellType: TextFieldState = TextFieldState(CellType.Fixed.name),
    onCellTypeChange: (String) -> Unit,
    onDismiss: () -> Unit = {}
) {
    var skipPartiallyExpanded by rememberSaveable { mutableStateOf(false) }
    val bottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState
    ) {
        Content(
            selectedType,
            selectedOrientation,
            onTypeSelected,
            onOrientationSelected,
            contentPadding,
            onContentPaddingChange,
            verticalSpacing,
            onVerticalSpacingChange,
            horizontalSpacing,
            onHorizontalSpacingChange,
            cellSize,
            onCellSizeChange,
            cellType,
            onCellTypeChange
        )
    }
}

@Composable
private fun ColumnScope.Content(
    selectedType: GridType = GridType.Basic,
    selectedOrientation: GridOrientation = GridOrientation.Vertical,
    onTypeSelected: (GridType) -> Unit = {},
    onOrientationSelected: (GridOrientation) -> Unit = {},
    contentPadding: Float = 0f,
    onContentPaddingChange: (Float) -> Unit = {},
    verticalSpacing: Float = 0f,
    onVerticalSpacingChange: (Float) -> Unit = {},
    horizontalSpacing: Float = 0f,
    onHorizontalSpacingChange: (Float) -> Unit = {},
    cellSize: Float = 0f,
    onGridCellSizeChange: (Float) -> Unit = {},
    cellType: TextFieldState = TextFieldState(CellType.Fixed.name),
    onCellTypeChange: (String) -> Unit = {}
) {
    Column(Modifier.padding(bottom = 24.dp)) {
        Row(Modifier.fillMaxWidth()) {
            RadioGroup(
                modifier = Modifier.weight(1f),
                title = "Tipo de cuadrícula",
                radioOptions = GridType.entries.map { it.name },
                selectedOption = selectedType.name,
                onOptionSelected = { onTypeSelected(GridType.valueOf(it)) })

            RadioGroup(
                modifier = Modifier.weight(1f),
                title = "Orientación",
                radioOptions = GridOrientation.entries.map { it.name },
                selectedOption = selectedOrientation.name,
                onOptionSelected = { onOrientationSelected(GridOrientation.valueOf(it)) }
            )

        }
        SliderSetting(
            title = "Content Padding",
            value = contentPadding,
            onValueChange = onContentPaddingChange,
            steps = 15,
            range = 0f..16f
        )
        SliderSetting(
            title = "Vertical Spacing",
            value = verticalSpacing,
            onValueChange = onVerticalSpacingChange,
            steps = 15,
            range = 0f..16f
        )
        SliderSetting(
            title = "Horizontal Spacing",
            value = horizontalSpacing,
            onValueChange = onHorizontalSpacingChange,
            steps = 15,
            range = 0f..16f
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        val options = CellType.entries.map { it.name }
        var expanded by rememberSaveable { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                state = cellType,
                readOnly = true,
                lineLimits = TextFieldLineLimits.SingleLine,
                label = { Text("GridCell") },
                trailingIcon = { TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                        onClick = {
                            onCellTypeChange(option)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }
        val cellTypeSelected = CellType.valueOf(cellType.text.toString())
        SliderSetting(
            title = when (cellTypeSelected) {
                CellType.Fixed -> "cols/rows"
                CellType.Adaptive,
                CellType.FixedSize -> "dp"
            },
            value = cellSize.coerceIn(rangeFor(cellTypeSelected)),
            onValueChange = onGridCellSizeChange,
            steps = stepFor(cellTypeSelected),
            range = rangeFor(cellTypeSelected),
        )
    }
}

fun stepFor(cellType: CellType) = when (cellType) {
    CellType.Fixed -> 1
    CellType.Adaptive, CellType.FixedSize -> 3
}

fun rangeFor(cellType: CellType) = when (cellType) {
    CellType.Fixed -> 2f..4f
    CellType.Adaptive, CellType.FixedSize -> 103f..206f
}

enum class CellType {
    Fixed, Adaptive, FixedSize
}

enum class GridOrientation {
    Vertical,
    Horizontal
}

@Preview
@Composable
private fun Preview() {
    Column {
        Content()
    }
}