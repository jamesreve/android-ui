package com.develou.datepickers.datepicker

data class DatePickerDialogState(
    val date: Long?,
    val isPickerVisible: Boolean,
    val onStart: () -> Unit,
    val onConfirmationClick: (Long?) -> Unit,
    val onDismissClick: () -> Unit
){
    val isPickerHidden get() = !isPickerVisible
}
