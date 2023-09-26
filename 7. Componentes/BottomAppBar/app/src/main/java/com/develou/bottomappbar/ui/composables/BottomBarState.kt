package com.develou.bottomappbar.ui.composables

/**
 * Representa el estado de una Bottom Bar
 */
data class BottomBarState(
    val actions: List<Action>,
    val floatingAction: Action? = null
)
