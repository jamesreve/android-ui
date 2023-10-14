package com.develou.bottomsheets.ui.components.modal

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.develou.bottomsheets.ui.viewmodel.ModalEvents
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheet(isVisible: Boolean, events: ModalEvents) {
    if (!isVisible) return

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val options = options(events)

    ModalBottomSheet(
        onDismissRequest = events.onMenuDismiss,
        sheetState = sheetState
    ) {
        Options(
            options = options
        ) {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    events.onMenuDismiss()
                }
            }
        }
    }
}