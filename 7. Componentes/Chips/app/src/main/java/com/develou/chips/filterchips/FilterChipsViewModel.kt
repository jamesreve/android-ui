package com.develou.chips.filterchips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develou.chips.filterchips.data.MemoryShoes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class FilterChipsViewModel : ViewModel() {

    private val selectedSizes = MutableStateFlow(emptySet<String>())

    val state = selectedSizes.map { selectedSizes ->
        FilterChipsState(
            MemoryShoes.findBy(selectedSizes).asUi(),
            mapSizesToFilterState(selectedSizes)
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FilterChipsState.Default
    )

    private fun mapSizesToFilterState(selectedSizes: Set<String>): List<FilterChipState> {
        return MemoryShoes.sizes.map { size ->
            FilterChipState(
                onClick = { updateSelectedSizes(size) },
                label = size,
                selected = size in selectedSizes
            )
        }
    }

    private fun updateSelectedSizes(clickedSize: String) {
        if (clickedSize in selectedSizes.value) {
            selectedSizes.update { it - clickedSize }
        } else {
            selectedSizes.update { it + clickedSize }
        }
    }
}