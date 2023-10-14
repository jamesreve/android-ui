package com.develou.bottomsheets.ui.components.standard

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.develou.bottomsheets.ui.components.modal.ModalBottomSheet
import com.develou.bottomsheets.ui.viewmodel.MainEvents
import com.develou.bottomsheets.ui.viewmodel.MainState
import com.develou.bottomsheets.ui.viewmodel.ModalEvents
import com.develou.bottomsheets.ui.viewmodel.partialHeightDp
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun StandardBottomSheet(mainState: MainState, events: MainEvents) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = mainState.partialHeightDp,
        sheetSwipeEnabled = mainState.swipeGestureEnabled,
        topBar = {
            TopBar(
                onShowModalSheetClick = { events.onShowModalSheetClick(true) },
                onExpandStandardSheet = {
                    scope.launch { scaffoldState.bottomSheetState.expand() }
                }
            )
        },
        sheetContent = { SheetContent(mainState, events) }
    ) { padding ->
        MainContent(
            padding = padding,
            sheetState = scaffoldState.bottomSheetState.currentValue.name,
            mainState = mainState
        )

        val setModalAction: (String) -> Unit = events.onLastActionChange

        ModalBottomSheet(
            isVisible = mainState.isModalSheetVisible,
            events = ModalEvents(
                onMenuDismiss = { events.onShowModalSheetClick(false) },
                onShareClick = { setModalAction("Compartir") },
                onGetLinkClick = { setModalAction("Obtener link") },
                onDeleteClick = { setModalAction("Eliminar") }
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    StandardBottomSheet(
        MainState.Default.copy(
            sheetPartiallyExpandedHeight = 348,
            sheetExpandedHeightPercentage = 100
        ),
        MainEvents.NoActions
    )
}