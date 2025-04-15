package com.develou.navigationbar.ui.base

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Composable para mostrar el ícono de la barra de navegación.
 */
@Composable
fun NavigationBarItemIcon(
    isSelected: Boolean,
    badge: BadgeType,
    selectedIcon: ImageVector,
    unselectedIcon: ImageVector
) {
    val iconContent: @Composable () -> Unit = {
        Icon(
            imageVector = if (isSelected) selectedIcon else unselectedIcon,
            contentDescription = null
        )
    }

    when (badge) {
        BadgeType.None -> iconContent()

        BadgeType.Circle -> {
            BadgedBox(badge = { Badge() }) {
                iconContent()
            }
        }

        is BadgeType.Numbered -> {
            BadgedBox(badge = {
                Badge {
                    Text(text = badge.numberString)
                }
            }) {
                iconContent()
            }
        }
    }
}