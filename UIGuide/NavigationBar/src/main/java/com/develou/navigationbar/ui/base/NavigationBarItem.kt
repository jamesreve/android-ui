package com.develou.navigationbar.ui.base

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItem <T: Any>(
    val label: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val badge: BadgeType = BadgeType.None,
    val route: T
)

sealed class BadgeType {
    data object None : BadgeType()
    data object Circle : BadgeType()
    data class Numbered(val number: Int) : BadgeType() {
        init {
            require(number in 0..99) {
                "El número de notificaciones debe estar entre 0 y 99"
            }
        }

        val numberString = number.toString()
    }
}

