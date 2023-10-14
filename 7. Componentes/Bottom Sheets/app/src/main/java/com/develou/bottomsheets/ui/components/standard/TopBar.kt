package com.develou.bottomsheets.ui.components.standard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material.icons.filled.LooksTwo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.develou.bottomsheets.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    onShowModalSheetClick: () -> Unit,
    onExpandStandardSheet: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(R.string.app_name)) },
        actions = {
            IconButton(onClick = onExpandStandardSheet) {
                Icon(
                    imageVector = Icons.Default.LooksOne,
                    contentDescription = "Mostrar sheet estándar"
                )
            }
            IconButton(onClick = onShowModalSheetClick) {
                Icon(
                    imageVector = Icons.Default.LooksTwo,
                    contentDescription = "Mostrar sheet modal"
                )
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    TopBar({}, {})
}