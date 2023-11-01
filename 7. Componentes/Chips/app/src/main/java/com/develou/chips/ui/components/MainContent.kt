package com.develou.chips.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.develou.chips.assistchips.AssistChips
import com.develou.chips.filterchips.FilterChips
import com.develou.chips.inputchips.InputChips
import com.develou.chips.suggestionchips.SuggestionChips

@Composable
fun MainContent(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        AssistChips(modifier = Modifier.weight(1f))

        HorizontalDivider()

        FilterChips(modifier = Modifier.weight(1f))

        HorizontalDivider()

        InputChips(modifier = Modifier.weight(1f))

        HorizontalDivider()

        SuggestionChips(modifier = Modifier.weight(1f))
    }
}