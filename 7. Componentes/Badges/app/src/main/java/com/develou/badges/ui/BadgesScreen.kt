package com.develou.badges.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develou.badges.ui.components.MainNavigationBar
import com.develou.badges.ui.theme.BadgesTheme
import com.develou.badges.ui.viewmodel.MainViewModel

private const val INITIAL_SELECTION = 0

@Composable
fun BadgesScreen(viewModel: MainViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    var selectedItem by remember { mutableIntStateOf(INITIAL_SELECTION) }

    Scaffold(
        bottomBar = {
            MainNavigationBar(
                state = state,
                onItemClick = { index -> selectedItem = index },
                selectedItem = selectedItem
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            BadgesContent(selectedItem, state)
        }
    }
}

@Composable
private fun BadgesContent(selectedItem: Int, state: MainState) {
    val content = when (selectedItem) {
        0 -> "Correos sin leer: ${state.emailsNotifications}"
        1 -> "Mensajes sin leer: ${state.chatNotifications}"
        2 -> "¿Grupos activos?: ${if (state.roomsIsActive) "Si" else "No"}"
        3 -> "Videollamadas perdidas: ${state.meetNotifications}"
        else -> "..."
    }
    Text(content)
}

data class MainState(
    val emailsNotifications: String,
    val chatNotifications: String,
    val roomsIsActive: Boolean,
    val meetNotifications: String
) {
    companion object {
        val Initial = MainState(
            emailsNotifications = "",
            chatNotifications = "",
            roomsIsActive = false,
            meetNotifications = ""
        )
    }
}

@Preview
@Composable
private fun Preview() {
    BadgesTheme {
        BadgesScreen()
    }
}