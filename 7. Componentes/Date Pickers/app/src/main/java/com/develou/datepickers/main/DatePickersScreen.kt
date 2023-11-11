package com.develou.datepickers.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.develou.datepickers.datepicker.CustomDatePickerDialog
import com.develou.datepickers.datepicker.DatePickerDialogViewModel
import com.develou.datepickers.daterangepicker.CustomDateRangePickerDialog
import com.develou.datepickers.daterangepicker.DateRangePickerDialogViewModel
import com.develou.datepickers.ui.theme.DatePickersTheme

@Composable
fun DatePickersScreen(
    datePickerViewModel: DatePickerDialogViewModel = viewModel(),
    rangePickerViewModel: DateRangePickerDialogViewModel = viewModel(),
    mainContentViewModel: DatePickersViewModel = viewModel()
) {
    val datePickerState by datePickerViewModel.state.collectAsState()
    val rangePickerState by rangePickerViewModel.state.collectAsState()
    val mainContentState by mainContentViewModel.state.collectAsState()

    CustomDatePickerDialog(
        state = datePickerState,
        onDateSelected = mainContentState.onDateSelected
    )

    CustomDateRangePickerDialog(
        state = rangePickerState,
        onConfirmation = mainContentState.onRangeSelected
    )

    Scaffold(
        topBar = { TopBar() },
        content = { padding ->
            DatePickersContent(
                padding = padding,
                state = mainContentState,
                onDateButtonClick = datePickerState.onStart,
                onRangeButtonClick = rangePickerState.onStart
            )
        }
    )
}

@Preview
@Composable
private fun Preview() {
    DatePickersTheme {
        DatePickersScreen()
    }
}