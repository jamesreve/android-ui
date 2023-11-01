package com.develou.chips.assistchips

import androidx.compose.ui.graphics.vector.ImageVector

data class AssistChipsState(
    val executedAction: String,
    val actions: List<AssistChipState>
) {
    companion object {
        val Default = AssistChipsState("Ninguna", emptyList())
    }
}

data class AssistChipState(
    val onClick: () -> Unit,
    val label: String,
    val icon: ImageVector
) {
    init {
        require(label.length <= 20)
    }
}