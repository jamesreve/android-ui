package com.develou.chips.filterchips

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FilterChips(
    modifier: Modifier,
    viewModel: FilterChipsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    FilterChips(state, modifier)
}

@Composable
private fun FilterChips(
    state: FilterChipsState,
    modifier: Modifier
) {
    Column(modifier = modifier.padding(top = 8.dp)) {
        Text("Filter Chip", style = MaterialTheme.typography.titleMedium)

        FilterChipsGroup(state.shoeFilter)

        Text(state.shoes)
    }
}

@Composable
private fun FilterChipsGroup(chips: List<FilterChipState>) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        chips.forEach { chip ->
            FilterChip(chip)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChip(state: FilterChipState) {
    FilterChip(
        selected = state.selected,
        onClick = state.onClick,
        label = { Text(state.label) },
        leadingIcon = if (state.selected) {
            {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Seleccionado",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        }
    )
}