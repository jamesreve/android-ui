package com.develou.bottomsheets.ui.components.standard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.develou.bottomsheets.ui.components.base.MediumSpace
import com.develou.bottomsheets.ui.components.base.SmallSpace
import com.develou.bottomsheets.ui.viewmodel.MainState
import com.develou.bottomsheets.ui.viewmodel.expandedHeightInMain
import com.develou.bottomsheets.ui.viewmodel.partialHeightString
import com.develou.bottomsheets.ui.viewmodel.swipeGestureEnabledString

@Composable
internal fun MainContent(
    padding: PaddingValues,
    sheetState: String,
    mainState: MainState
) {
    Column(
        modifier = Modifier
            .padding(padding)
            .padding(16.dp)
    ) {

        Title("Standard Bottom Sheet")

        MediumSpace()

        Property("Estado", sheetState)

        SmallSpace()

        Property("Swipe habilitado", mainState.swipeGestureEnabledString)

        SmallSpace()

        Property("Altura al estar parcialmente expandida", mainState.partialHeightString)

        SmallSpace()

        Property("Altura al estar expandida", mainState.expandedHeightInMain)

        Divider(Modifier.padding(vertical = 16.dp))

        Title("Modal Bottom Sheet")

        MediumSpace()

        Property("Acción", mainState.lastModalSheetAction)
    }
}

@Composable
private fun Property(label: String, body: String) {
    Column(
        Modifier
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(body, style = MaterialTheme.typography.bodyLarge)
    }
}


@Composable
private fun Title(text: String) {
    Text(text, style = MaterialTheme.typography.titleMedium)
}

@Preview
@Composable
private fun Preview() {
    MainContent(
        padding = PaddingValues(0.dp),
        sheetState = "Expanded",
        mainState = MainState.Default
    )
}