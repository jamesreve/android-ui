package com.develou.badges.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class SmallBadgedIconState(
    val isVisible: Boolean,
    val icon: ImageVector,
    val description: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallBadgedIcon(state: SmallBadgedIconState) {
    BadgedBox(
        badge = {
            if (state.isVisible) {
                Badge(modifier = Modifier.offset(x = (-3).dp, y = 3.dp))
            }
        }) {
        Icon(
            imageVector = state.icon,
            contentDescription = state.description
        )
    }
}

@Composable
@Preview
private fun Preview() {
    Row(
        modifier = Modifier
            .size(64.dp)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        SmallBadgedIcon(
            state = SmallBadgedIconState(
                isVisible = true,
                icon = Icons.Default.Folder,
                description = "Folders"
            )
        )
        SmallBadgedIcon(
            state = SmallBadgedIconState(
                isVisible = false,
                icon = Icons.Default.Folder,
                description = "Folders"
            )
        )
    }
}