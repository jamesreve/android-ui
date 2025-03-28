package com.develou.timepickers.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.develou.timepickers.R
import com.develou.timepickers.main.viewmodel.TimePickersState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickersContent(
    state: TimePickersState,
    onStartClick: () -> Unit,
    onEndClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        },
        content = { padding ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    TimeTextField(
                        label = "Inicio",
                        text = state.startTimeString(),
                        onClick = onStartClick
                    )

                    MediumSpace()

                    TimeTextField(
                        label = "Fin",
                        text = state.endTimeString(),
                        onClick = onEndClick
                    )
                }

                MediumSpace()

                Text(text = "Duración: ${state.duration()}")
            }
        }
    )
}

@Composable
private fun MediumSpace() {
    Spacer(modifier = Modifier.width(16.dp))
}

@Composable
private fun RowScope.TimeTextField(
    label: String,
    text: String,
    onClick: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        modifier = Modifier
            .weight(1f)
            .focusRequester(focusRequester),
        label = {
            Text(label)
        },
        value = text,
        onValueChange = {},
        readOnly = true,
        leadingIcon = {
            IconButton(
                onClick = {
                    focusRequester.requestFocus()
                    onClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.AccessTime,
                    contentDescription = null
                )
            }
        }
    )
}