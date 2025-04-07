package com.develou.grids.ui.main.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SliderSetting(
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    steps: Int,
    range: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Title(text = "$title ${value.toInt()}")
        Slider(
            modifier = Modifier.Companion.padding(horizontal = 16.dp),
            value = value,
            onValueChange = onValueChange,
            valueRange = range,
            steps = steps
        )
    }
}