package com.develou.tooltips.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.develou.tooltips.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar() {
    CenterAlignedTopAppBar(
        title = { AppBarTitle() },
        actions = { SettingsAction() }
    )
}

@Composable
private fun AppBarTitle() {
    Text(stringResource(R.string.app_name))
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SettingsAction() {
    TooltipBox(
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = { SettingsTooltip() },
        state = rememberTooltipState(),
        content = { SettingsIconButton() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsTooltip() {
    RichTooltip(
        title = { Text("Ajustes") },
        text = { Text("Preferencias de tu cuenta y comportamiento de la App") },
    )
}

@Composable
private fun SettingsIconButton() {
    IconButton(onClick = {}) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = "Ajustes"
        )
    }
}

@Preview
@Composable
private fun Preview() {
    TopBar()
}