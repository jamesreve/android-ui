package com.develou.chips.suggestionchips

data class SuggestionChipsState(
    val suggestions: List<SuggestionState>,
    val greeting: String,
    val isPanelVisible: Boolean
) {
    companion object {
        val Default = SuggestionChipsState(
            suggestions = emptyList(),
            greeting = "",
            isPanelVisible = false
        )
    }
}

data class SuggestionState(
    val onClick: () -> Unit,
    val label: String
)