package com.develou.chips.assistchips

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AssistChips(
    modifier: Modifier,
    viewModel: AssistChipsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState(initial = AssistChipsState.Default)
    AssistChips(modifier, state)
}

@Composable
private fun AssistChips(
    modifier: Modifier = Modifier,
    state: AssistChipsState
) {

    Column(modifier = modifier) {
        Text("Assist Chip", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.size(8.dp))

        AssistChipGroup(state.actions)
        
        Text("Chip accionada: ${state.executedAction}")
    }
}

@Composable
private fun AssistChipGroup(
    chipStates: List<AssistChipState>
) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        chipStates.forEach { chipState ->
            AssistChip(state = chipState)
        }
    }
}

@Composable
fun AssistChip(state: AssistChipState) {
    ElevatedAssistChip(
        onClick = state.onClick,
        label = { Text(state.label) },
        leadingIcon = {
            Icon(
                imageVector = state.icon,
                contentDescription = state.label,
                modifier = Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}

@Preview
@Composable
private fun Preview() {
    AssistChips(state = AssistChipsState.Default)
}