package com.develou.bottomappbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.develou.bottomappbar.ui.composables.Action
import com.develou.bottomappbar.ui.composables.BaseBottomBar
import com.develou.bottomappbar.ui.composables.BottomBarState
import com.develou.bottomappbar.ui.composables.FAB
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val userActions: (String) -> Unit = { actionClicked ->
        scope.launch {
            snackbarHostState.showSnackbar(message = actionClicked)
        }
    }
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            MainBottomBar(
                userActions = userActions,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FAB(
                floatingAction = Action.vectorAction(
                    imageVector = Icons.Default.Add,
                    description = "Añadir",
                    action = { userActions("Añadir") })
            )
        },
        floatingActionButtonPosition = FabPosition.EndOverlay
    ) {
        ExampleList(padding = it)
    }
}

@Composable
private fun ExampleList(padding: PaddingValues) {
    LazyColumn(
        contentPadding = padding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(20) {
            ExampleItem()
        }
    }
}

@Composable
private fun ExampleItem() {
    ListItem(
        headlineContent = {
            Text("Encabezado")
        },
        supportingContent = {
            Text("Texto de apoyo")
        },
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = Color.LightGray,
                        shape = CircleShape
                    )
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = null
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainBottomBar(
    userActions: (String) -> Unit,
    scrollBehavior: BottomAppBarScrollBehavior? = null
) {
    BaseBottomBar(
        state = BottomBarState(
            actions = listOf(
                Action.vectorAction(
                    imageVector = Icons.Outlined.Search,
                    description = "Buscar",
                    action = { userActions("Buscar") }),
                Action.vectorAction(
                    imageVector = Icons.Outlined.LocationOn,
                    description = "Localizar",
                    action = { userActions("Localizar") }),
                Action.vectorAction(
                    imageVector = Icons.Outlined.Delete,
                    description = "Eliminar",
                    action = { userActions("Eliminar") }),
                Action.vectorAction(
                    imageVector = Icons.Outlined.Settings,
                    description = "Ajustes",
                    action = { userActions("Ajustes") }),
                Action.vectorAction(
                    imageVector = Icons.Outlined.Info,
                    description = "Ayuda",
                    action = { userActions("Ayuda") })
            )
        ),
        scrollBehavior = scrollBehavior
    )
}