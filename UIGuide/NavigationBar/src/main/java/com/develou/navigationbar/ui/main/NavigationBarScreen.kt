package com.develou.navigationbar.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.develou.navigationbar.R
import com.develou.navigationbar.ui.base.NavigationBarItemIcon
import com.develou.navigationbar.ui.base.NavigationBarItems
import com.develou.navigationbar.ui.others.Home
import com.develou.navigationbar.ui.others.exploreScreen
import com.develou.navigationbar.ui.others.homeScreen
import com.develou.navigationbar.ui.others.notificationsScreen
import com.develou.navigationbar.ui.others.profileScreen
import kotlinx.coroutines.launch

@Composable
fun NavigationBarScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val homeScrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            ExampleNavigationBar(
                items = ItemsExample,
                selectedItem = currentDestination,
                onItemClick = {
                    if (currentDestination.isRoute(Home) && it == Home) {
                        scope.launch {
                            homeScrollState.animateScrollTo(0)
                        }
                        return@ExampleNavigationBar
                    }
                    navController.navigate(it) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) {
        NavHost(navController, startDestination = Home, modifier = Modifier.padding(it)) {
            homeScreen(homeScrollState)
            exploreScreen()
            notificationsScreen()
            profileScreen()
        }
    }
}

fun NavDestination?.isRoute(route: Any) = this?.hasRoute(route::class) == true

@Composable
private fun ExampleNavigationBar(
    items: NavigationBarItems,
    selectedItem: NavDestination? = null,
    onItemClick: (Any) -> Unit = {}
) {
    NavigationBar {
        items.items.forEach { item ->
            val selected = selectedItem.isRoute(item.route)
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item.route) },
                icon = {
                    NavigationBarItemIcon(
                        isSelected = selected,
                        badge = item.badge,
                        selectedIcon = item.selectedIcon,
                        unselectedIcon = item.unselectedIcon
                    )
                },
                label = { Text(item.label) },
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(R.string.app_name))
        }
    )
}


@Composable
@Preview
private fun NavigationBarScreenPreview() {
    NavigationBarScreen()
}