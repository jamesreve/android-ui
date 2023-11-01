package com.develou.chips.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.develou.chips.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar() {
    CenterAlignedTopAppBar(title = { AppBarTitle() })
}

@Composable
private fun AppBarTitle() {
    Text(stringResource(R.string.app_name))
}

@Preview
@Composable
private fun Preview() {
    TopBar()
}