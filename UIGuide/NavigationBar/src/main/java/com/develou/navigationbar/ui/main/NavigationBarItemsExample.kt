package com.develou.navigationbar.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import com.develou.navigationbar.ui.base.BadgeType
import com.develou.navigationbar.ui.base.NavigationBarItem
import com.develou.navigationbar.ui.base.NavigationBarItems
import com.develou.navigationbar.ui.others.Explore
import com.develou.navigationbar.ui.others.Home
import com.develou.navigationbar.ui.others.Notifications
import com.develou.navigationbar.ui.others.Profile

val ItemsExample = NavigationBarItems(
    items = listOf(
        NavigationBarItem(
            label = "Inicio",
            unselectedIcon = Icons.Outlined.Home,
            selectedIcon = Icons.Filled.Home,
            badge = BadgeType.None,
            route = Home
        ),
        NavigationBarItem(
            label = "Explorar",
            unselectedIcon = Icons.Outlined.Search,
            selectedIcon = Icons.Filled.Search,
            badge = BadgeType.None,
            route = Explore
        ),
        NavigationBarItem(
            label = "Avisos",
            unselectedIcon = Icons.Outlined.Notifications,
            selectedIcon = Icons.Filled.Notifications,
            badge = BadgeType.Numbered(10),
            route = Notifications
        ),
        NavigationBarItem(
            label = "Perfil",
            unselectedIcon = Icons.Outlined.Person,
            selectedIcon = Icons.Filled.Person,
            badge = BadgeType.Circle,
            route = Profile
        )
    )
)