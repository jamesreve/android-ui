package com.develou.chips.assistchips

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Videocam
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class AssistChipsViewModel : ViewModel() {

    private val executedAction = MutableStateFlow("Ninguna")
    private val actions = listOf(
        AssistChipState(
            onClick = { onActionClick("Llamada") },
            label = "Llamada",
            icon = Icons.Default.Call
        ),
        AssistChipState(
            onClick = { onActionClick("Mensaje") },
            label = "Mensaje",
            icon = Icons.AutoMirrored.Default.Message
        ),
        AssistChipState(
            onClick = { onActionClick("Videollamada") },
            label = "Videollamada",
            icon = Icons.Default.Videocam
        )
    )


    val state = executedAction.map { action ->
        AssistChipsState(executedAction = action, actions = actions)
    }

    private fun onActionClick(action: String) {
        executedAction.value = action
    }
}