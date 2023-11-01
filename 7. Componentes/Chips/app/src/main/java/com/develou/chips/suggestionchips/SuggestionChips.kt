package com.develou.chips.suggestionchips

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develou.chips.ui.components.SmallSpace

@Composable
fun SuggestionChips(
    modifier: Modifier = Modifier,
    viewModel: SuggestionChipsViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState(SuggestionChipsState.Default)
    SuggestionChips(modifier, state)
}

@Composable
private fun SuggestionChips(
    modifier: Modifier,
    state: SuggestionChipsState
) {
    Column(modifier = modifier) {
        SmallSpace()

        Text("Suggestion Chip", style = MaterialTheme.typography.titleMedium)

        SmallSpace()

        Text("Chat: ${state.greeting}")

        if (!state.isPanelVisible) return

        SuggestionChipsGroup(state.suggestions)
    }
}

@Composable
private fun SuggestionChipsGroup(chips: List<SuggestionState>) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        chips.forEach {
            SuggestionChip(it)
        }
    }
}

@Composable
private fun SuggestionChip(chipState: SuggestionState) {
    SuggestionChip(
        onClick = chipState.onClick,
        label = { Text(chipState.label) }
    )
}

@Preview
@Composable
private fun Preview() {
    SuggestionChips(modifier = Modifier, SuggestionChipsState.Default)
}