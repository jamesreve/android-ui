package com.develou.bottomsheets.ui.viewmodel

const val DefaultPartiallyExpandedHeight = 56

data class MainState(
    val swipeGestureEnabled: Boolean,
    val sheetPartiallyExpandedHeight: Int,
    val sheetExpandedHeightPercentage: Int,
    val isModalSheetVisible: Boolean,
    val lastModalSheetAction: String
) {
    init {
        require(sheetExpandedHeightPercentage in 25..100)
        require(sheetPartiallyExpandedHeight >= DefaultPartiallyExpandedHeight)
    }

    companion object {
        val Default = MainState(
            swipeGestureEnabled = true,
            sheetPartiallyExpandedHeight = DefaultPartiallyExpandedHeight,
            sheetExpandedHeightPercentage = 25,
            isModalSheetVisible = false,
            lastModalSheetAction = "Ninguna"
        )
    }
}