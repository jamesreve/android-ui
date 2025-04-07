package com.develou.grids.ui.main.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Title(text: String) {
    Text(
        modifier = Modifier.Companion.padding(start = 16.dp, top = 16.dp),
        text = text,
        style = MaterialTheme.typography.labelMedium
    )
}