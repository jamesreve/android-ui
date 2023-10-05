package com.develou.badges.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.develou.badges.ui.MainState

data class NavigationBarIconState(
    val label: String,
    val icon: ImageVector,
    val badgeType: BadgeType
)

sealed class BadgeType {
    data object None : BadgeType()
    data class Small(val active: Boolean) : BadgeType()
    data class Large(val counter: String) : BadgeType()
}

@Composable
fun MainNavigationBar(
    state: MainState,
    onItemClick: (Int) -> Unit,
    selectedItem: Int
) {
    val items = listOf(
        NavigationBarIconState(
            label = "Emails",
            icon = Icons.Default.Email,
            badgeType = BadgeType.Large(state.emailsNotifications)
        ),
        NavigationBarIconState(
            label = "Chat",
            icon = Icons.Default.ChatBubble,
            badgeType = BadgeType.Large(state.chatNotifications)
        ),
        NavigationBarIconState(
            label = "Rooms",
            icon = Icons.Default.Groups,
            badgeType = BadgeType.Small(state.roomsIsActive)
        ),
        NavigationBarIconState(
            label = "Meet",
            icon = Icons.Default.Videocam,
            badgeType = BadgeType.Large(state.meetNotifications)
        )
    )

    NavigationBar {
        NavigationIcons(items, selectedItem, onItemClick)
    }
}

@Composable
private fun RowScope.NavigationIcons(
    items: List<NavigationBarIconState>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    items.forEachIndexed { index, item ->
        NavigationItem(
            item = item,
            isSelected = index == selectedItem,
            onItemClick = { onItemClick(index) }
        )
    }
}

@Composable
private fun RowScope.NavigationItem(
    item: NavigationBarIconState,
    isSelected: Boolean,
    onItemClick: () -> Unit
) {
    NavigationBarItem(
        icon = {
            ItemIcon(item)
        },
        selected = isSelected,
        onClick = onItemClick,
        label = {
            Text(item.label)
        }
    )
}

@Composable
private fun ItemIcon(
    item: NavigationBarIconState,
) {
    when (item.badgeType) {
        BadgeType.None -> Icon(
            imageVector = item.icon,
            contentDescription = item.label
        )

        is BadgeType.Small -> SmallBadgedIcon(
            state = SmallBadgedIconState(
                isVisible = item.badgeType.active,
                icon = item.icon,
                description = item.label
            )
        )

        is BadgeType.Large -> LargeBadgedIcon(
            LargeBadgeState(
                number = item.badgeType.counter,
                icon = item.icon,
                description = item.label
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MainNavigationBar(
        state = MainState(
            emailsNotifications = "999+",
            chatNotifications = "10",
            roomsIsActive = true,
            meetNotifications = "3"
        ),
        onItemClick = {},
        selectedItem = 0
    )
}