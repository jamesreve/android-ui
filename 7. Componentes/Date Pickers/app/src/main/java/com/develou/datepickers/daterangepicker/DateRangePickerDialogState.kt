package com.develou.datepickers.daterangepicker

import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import com.develou.datepickers.util.asInstant
import com.develou.datepickers.util.asMonthAndDayString

data class DateRangePickerDialogState(
    val startDate: Long?,
    val endDate: Long?,
    val isPickerVisible: Boolean,
    val onStart:()->Unit,
    val onConfirmationClick: (Long?, Long?) -> Unit,
    val onDismissClick: () -> Unit
) {
    val isPickerHidden get() = !isPickerVisible
}

@OptIn(ExperimentalMaterial3Api::class)
val DateRangePickerState.headline:String get() {
    val start = selectedStartDateMillis.asInstant()?.asMonthAndDayString()?:"Inicio"
    val end = selectedEndDateMillis.asInstant()?.asMonthAndDayString()?:"Fin"

    return "$start - $end"
}
