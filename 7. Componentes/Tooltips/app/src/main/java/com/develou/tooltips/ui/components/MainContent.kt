package com.develou.tooltips.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TooltipState
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun MainContent(
    padding: PaddingValues
) {
    Box(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        NewFeaturePanel()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun NewFeaturePanel() {
    val state = rememberTooltipState(
        initialIsVisible = true,
        isPersistent = true
    )
    TooltipBox(
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = { PersistentRichTooltip(state) },
        enableUserInput = false,
        state = state,
        content = { PendingTasksInfo(state.isVisible) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PersistentRichTooltip(state: TooltipState) {
    RichTooltip(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                NewReleaseIcon()
                Spacer(Modifier.size(4.dp))
                Text("Nueva característica disponible")
            }
        },
        action = {
            TextButton(onClick = { state.dismiss() }) {
                Text("Lo tengo")
            }
        },
        text = {
            Text(
                "Ahora puedes ver la cantidad de tareas pendientes en " +
                        "este panel para mantenerte al tanto de tu progreso"
            )
        }
    )
}


@Composable
private fun NewReleaseIcon() {
    Icon(
        imageVector = Icons.Default.AutoAwesome,
        contentDescription = null,
        tint = newReleaseIconColor()
    )
}

@Composable
private fun PendingTasksInfo(isFocused: Boolean) {
    Column(
        Modifier
            .width(300.dp)
            .height(56.dp)
    ) {

        Text(
            text = "Cantidad de tareas pendientes: 10",
            modifier = Modifier
                .background(color = pendingTasksInfoColor(isFocused))
                .border(
                    width = Dp.Hairline,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(
                        4.dp
                    )
                )
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun pendingTasksInfoColor(isFocused: Boolean): Color {
    return if (isFocused)
        MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
    else
        MaterialTheme.colorScheme.surface
}

@Composable
private fun newReleaseIconColor(): Color {
    val infiniteTransition = rememberInfiniteTransition(label = "A1")
    val color by infiniteTransition.animateColor(
        initialValue = Color(0xffffbc42),
        targetValue = Color(0xff0496ff),
        animationSpec = infiniteRepeatable(
            animation = tween(1_000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "A2"
    )
    return color
}