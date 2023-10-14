package com.develou.bottomsheets.ui.viewmodel

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val MainState.expandedHeightInMain get() = "$sheetExpandedHeightPercentage%"

val MainState.partialHeightString get() = sheetPartiallyExpandedHeight.toString() + "dp"

val MainState.swipeGestureEnabledString get() = if (swipeGestureEnabled) "Si" else "No"

val MainState.expandedHeightString get() = sheetExpandedHeightPercentage.toString()

val MainState.partialHeightDp get() = sheetPartiallyExpandedHeight.dp

fun MainState.expandedHeightDp(screenHeightDp: Int): Dp {
    return (screenHeightDp * sheetExpandedHeightPercentage / 100).dp
}