package com.develou.bottomsheets.ui.components.base

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MediumSpace() {
    Spacer(modifier = Modifier.size(16.dp))
}

@Composable
fun SmallSpace() {
    Spacer(modifier = Modifier.size(8.dp))
}