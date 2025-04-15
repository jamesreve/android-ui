package com.develou.navigationbar.ui.base

data class NavigationBarItems(
    val items: List<NavigationBarItem<*>>,
) {
    init {
        require(items.size >= 3) {
            "La Navigation Bar debe tener al menos 3 items. " +
                    "Usa Tabs en su lugar si tienes menos."
        }
        require(items.size <= 5) {
            "La Navigation Bar debe tener como máximo 5 items. " +
                    "Usa Navigation Drawer en su lugar si tienes más."
        }
    }
}