package com.develou.datepickers.main

data class DatePickersState(
    val dateSelected: String,
    val rangeSelected: String,
    val onDateSelected: (Long?) -> Unit,
    val onRangeSelected: (Long?, Long?) -> Unit
)