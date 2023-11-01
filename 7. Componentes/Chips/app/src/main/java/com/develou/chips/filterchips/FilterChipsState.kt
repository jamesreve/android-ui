package com.develou.chips.filterchips

data class FilterChipsState(
    val shoes: String,
    val shoeFilter: List<FilterChipState>
) {
    companion object {
        val Default = FilterChipsState("...", emptyList())
    }
}

data class FilterChipState(
    val onClick: () -> Unit,
    val label: String,
    val selected: Boolean
)