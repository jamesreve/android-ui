package com.develou.grids.ui.main

data class GridItemUiModel(
    val id: String,
    val title: String,
    val hexColor: Long = range32BitsColor.random()
) {
    val sizeForStaggered = MIN_SIZE * rangeForRandom.random()

    companion object {
        private val rangeForRandom = 1..3
        private val range32BitsColor = (0x00000000..0xFFFFFFFF)

        var idCounter = 1

        fun newId() = idCounter++.toString()

        const val MIN_SIZE = 120
    }
}