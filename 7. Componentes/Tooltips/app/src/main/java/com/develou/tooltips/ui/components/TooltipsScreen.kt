package com.develou.tooltips.ui.components

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.develou.tooltips.ui.theme.TootipsTheme

@Composable
fun TooltipsScreen() {
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = { Fab() },
        content = { padding -> MainContent(padding) }
    )
}

@Preview
@Composable
private fun Preview() {
    TootipsTheme {
        TooltipsScreen()
    }
}