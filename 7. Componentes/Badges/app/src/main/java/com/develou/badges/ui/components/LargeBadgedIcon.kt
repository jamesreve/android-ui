package com.develou.badges.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class LargeBadgeState(
    val number: String,
    val icon: ImageVector,
    val description: String
) {
    init {
        require(number.length <= 4)
    }

    val isVisible = number.isNotBlank()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeBadgedIcon(state: LargeBadgeState) {
    BadgedBox(
        badge = {
            if (state.isVisible) {
                Badge(Modifier.offset(x = (-8).dp, y = 8.dp)) {
                    Text(
                        text = state.number,
                        modifier = Modifier.semantics {
                            contentDescription = "${state.number} notificaciones nuevas"
                        }
                    )
                }
            }
        }) {
        Icon(
            imageVector = state.icon,
            contentDescription = state.description
        )
    }
}

@Preview
@Composable
private fun Preview() {
    Box(
        modifier = Modifier.size(64.dp),
        contentAlignment = Alignment.Center
    ) {
        LargeBadgedIcon(
            LargeBadgeState(
                number = "9+",
                icon = Icons.Default.LibraryBooks,
                description = ""
            )
        )
    }
}
