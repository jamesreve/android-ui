package com.develou.datepickers.util

import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

fun formatDate(date: Long?): String {
    return date?.asInstant()?.asShortString() ?: "Seleccionar fecha"
}

fun formatRange(start: Long?, end: Long?): String {
    if (start == null || end == null) return "Seleccionar rango"

    val startDate = Instant.ofEpochMilli(start).asShortString()
    val endDate = Instant.ofEpochMilli(end).asShortString()

    return "De $startDate a $endDate"
}

fun Instant.asShortString(): String {
    return format("dd/MM/yyyy")
}

fun Instant.asMonthAndDayString(): String {
    return format("MMM dd").replaceFirstChar { it.titlecase() }
}

fun Instant.format(pattern: String): String {
    return DateTimeFormatter
        .ofPattern(pattern)
        .withZone(ZoneOffset.UTC)
        .format(this)
}

fun Long?.asInstant(): Instant? = this?.let { Instant.ofEpochMilli(it) }