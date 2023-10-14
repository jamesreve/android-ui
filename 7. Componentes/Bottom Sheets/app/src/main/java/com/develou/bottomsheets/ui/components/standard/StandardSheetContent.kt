package com.develou.bottomsheets.ui.components.standard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develou.bottomsheets.ui.components.base.ExposedDropdownMenu
import com.develou.bottomsheets.ui.components.base.ExposedDropdownMenuState
import com.develou.bottomsheets.ui.components.base.LabelledSwitch
import com.develou.bottomsheets.ui.components.base.MediumSpace
import com.develou.bottomsheets.ui.viewmodel.MainEvents
import com.develou.bottomsheets.ui.viewmodel.MainState
import com.develou.bottomsheets.ui.viewmodel.expandedHeightDp
import com.develou.bottomsheets.ui.viewmodel.expandedHeightString
import com.develou.bottomsheets.ui.viewmodel.partialHeightString

@Composable
internal fun SheetContent(
    state: MainState,
    events: MainEvents
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val sheetHeightDp = state.expandedHeightDp(screenHeight)

    Column(
        Modifier
            .fillMaxWidth()
            .height(sheetHeightDp)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        Text(
            text = "Bottom Sheet Estándar",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        MediumSpace()

        LabelledSwitch(
            checked = state.swipeGestureEnabled,
            label = "sheetSwipeEnable",
            onCheckedChange = { events.setSwipe(it) }
        )

        MediumSpace()

        PartiallyExpandedHeightField(
            onOptionSelection = { peekHeight ->
                events.setPartiallyExpandedHeight(peekHeight)
            },
            optionSelected = state.partialHeightString
        )

        MediumSpace()

        ExpandedHeightField(
            sizeSelected = state.expandedHeightString,
            onSizeSelection = { size ->
                events.setExpandedHeight(size)
            }
        )
    }
}

@Composable
private fun ExpandedHeightField(
    sizeSelected: String,
    onSizeSelection: (String) -> Unit
) {
    ExposedDropdownMenu(
        ExposedDropdownMenuState(
            label = "Altura de Sheet (%)",
            options = listOf("25", "50", "100"),
            optionSelected = sizeSelected,
            onOptionSelection = onSizeSelection
        )
    )
}

@Composable
private fun PartiallyExpandedHeightField(
    onOptionSelection: (String) -> Unit,
    optionSelected: String
) {
    ExposedDropdownMenu(
        ExposedDropdownMenuState(
            label = "peekHeight (Dp)",
            options = listOf("56", "156", "256", "348"),
            optionSelected = optionSelected,
            onOptionSelection = onOptionSelection
        )
    )
}

@Preview
@Composable
private fun Preview() {
    SheetContent(
        state = MainState.Default.copy(sheetExpandedHeightPercentage = 50),
        events = MainEvents.NoActions
    )
}