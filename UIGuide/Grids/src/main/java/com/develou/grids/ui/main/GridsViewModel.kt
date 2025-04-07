package com.develou.grids.ui.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GridsViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<GridItemUiModel>>(
        List(20) { newItem() }.reversed()
    )

    val items = _items.asStateFlow()

    fun addItem() {
        _items.update {
            listOf(newItem()) + it
        }
    }

    private fun newItem(): GridItemUiModel {
        val itemId = GridItemUiModel.newId()
        return GridItemUiModel(itemId, itemId)
    }

    fun removeItem(item: GridItemUiModel) {
        _items.update { it - item }
    }
}