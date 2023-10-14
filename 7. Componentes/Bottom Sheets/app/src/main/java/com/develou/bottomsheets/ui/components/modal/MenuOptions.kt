package com.develou.bottomsheets.ui.components.modal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.develou.bottomsheets.ui.viewmodel.ModalEvents

data class ImageMenuOptionState(
    val icon: ImageVector,
    val label: String,
    val onClick: () -> Unit
)

@Composable
internal fun Options(
    options: List<ImageMenuOptionState>,
    hideSheet: () -> Unit
) {
    options.forEach { option ->
        Option(state = option, hideSheet = hideSheet)
    }
}

@Composable
private fun Option(state: ImageMenuOptionState, hideSheet: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable {
            state.onClick()
            hideSheet()
        },
        leadingContent = {
            Icon(
                imageVector = state.icon,
                contentDescription = state.label
            )
        },
        headlineContent = {
            Text(state.label)
        }
    )
}


@Composable
fun options(state: ModalEvents): List<ImageMenuOptionState> {
    return listOf(
        ImageMenuOptionState(
            icon = Icons.Outlined.Share,
            label = "Compartir",
            onClick = state.onShareClick
        ),
        ImageMenuOptionState(
            icon = Icons.Outlined.Link,
            label = "Obtener link",
            onClick = state.onGetLinkClick
        ),
        ImageMenuOptionState(
            icon = Icons.Outlined.Delete,
            label = "Eliminar",
            onClick = state.onDeleteClick
        )
    )
}

@Preview
@Composable
private fun Preview() {
    Column {
        Options(options = options(state = ModalEvents.NoActions)) { }
    }
}