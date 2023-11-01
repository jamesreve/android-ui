package com.develou.chips.ui.components

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.develou.chips.ui.theme.ChipsTheme

@Composable
fun ChipsScreen() {
    Scaffold(
        topBar = { TopBar() },
        content = { padding -> MainContent(padding) }
    )
}

@Preview
@Composable
private fun Preview() {
    ChipsTheme {
        ChipsScreen()
    }
}