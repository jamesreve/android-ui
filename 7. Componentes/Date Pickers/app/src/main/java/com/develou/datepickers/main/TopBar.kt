package com.develou.datepickers.main

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.develou.datepickers.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar() {
    CenterAlignedTopAppBar(title = { Title() })
}

@Composable
private fun Title() {
    Text(stringResource(R.string.app_name))
}

@Preview
@Composable
private fun Preview() {
    TopBar()
}