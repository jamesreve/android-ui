package com.develou.bottomsheets.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develou.bottomsheets.ui.components.standard.StandardBottomSheet
import com.develou.bottomsheets.ui.theme.BottomSheetsTheme
import com.develou.bottomsheets.ui.viewmodel.MainEvents
import com.develou.bottomsheets.ui.viewmodel.MainViewModel

@Composable
fun BottomSheetsScreen(viewModel: MainViewModel = viewModel()) {
    val mainState by viewModel.state.collectAsState()

    StandardBottomSheet(
        mainState = mainState,
        events = sheetEvents(viewModel)
    )
}

@Composable
internal fun sheetEvents(viewModel: MainViewModel): MainEvents {
    return MainEvents(
        setSwipe = viewModel::updateSwipe,
        setExpandedHeight = viewModel::updateExpandedHeight,
        setPartiallyExpandedHeight = viewModel::updatePartiallyHeight,
        onShowModalSheetClick = viewModel::updateModalVisibility,
        onLastActionChange = viewModel::updateLastModalAction
    )
}

@Preview
@Composable
private fun Preview() {
    BottomSheetsTheme {
        BottomSheetsScreen()
    }
}