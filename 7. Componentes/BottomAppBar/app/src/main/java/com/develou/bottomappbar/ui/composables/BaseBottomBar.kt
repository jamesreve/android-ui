package com.develou.bottomappbar.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseBottomBar(
    state: BottomBarState,
    scrollBehavior: BottomAppBarScrollBehavior? = null
) {
    BottomAppBar(
        actions = {
            Actions(state.actions)
        },
        floatingActionButton = {
            FAB(state.floatingAction)
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
fun FAB(floatingAction: Action?) {
    floatingAction?.let {
        FloatingActionButton(onClick = it.action) {
            Action(action = it)
        }
    }
}

@Composable
private fun Actions(actions: List<Action>) {
    if (actions.size < 2)
        error("Una Bottom Bar requiere mínimo dos acciones")

    if (actions.size > 4) {
        ActionButtonsWithOverflow(actions)
    } else {
        ActionButtons(actions)
    }
}

@Composable
private fun ActionButtonsWithOverflow(actions: List<Action>) {
    val (expanded, setExpanded) = remember {
        mutableStateOf(false)
    }

    OverflowAction {
        setExpanded(true)
    }

    ActionButtons(actions.take(3))

    OverflowMenu(
        expanded = expanded,
        onExpandedChange = setExpanded,
        actions = actions.takeLast(actions.size - 3),
    )
}

@Composable
private fun OverflowAction(onClick: () -> Unit) {
    Action(
        action = Action.vectorAction(
            imageVector = Icons.Default.MoreVert,
            description = "Ver más",
            action = onClick
        )
    )
}

@Composable
private fun ActionButtons(actions: List<Action>) {
    actions.reversed().forEach { action ->
        Action(action)
    }
}

@Composable
private fun Action(action: Action) {
    IconButton(onClick = action.action) {
        when (action.icon) {
            is ImageVector -> Icon(
                imageVector = action.icon,
                contentDescription = action.description
            )

            is Painter -> Icon(
                painter = action.icon,
                contentDescription = action.description
            )
        }
    }
}

@Composable
private fun OverflowMenu(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    actions: List<Action>,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onExpandedChange(false) },
        modifier = modifier
    ) {
        actions.forEach {
            DropdownMenuItem(
                text = {
                    Text(
                        text = it.description,
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                onClick = {
                    it.action()
                    onExpandedChange(false)
                },
            )
        }
    }
}
