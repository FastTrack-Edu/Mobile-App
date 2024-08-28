package com.nizamsetiawan.app.fasttrackedu.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun String.toTime(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ISO_INSTANT
        val instant = Instant.from(formatter.parse(this))
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        DateTimeFormatter.ofPattern("dd MMMM").format(localDateTime)
    } else {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        simpleDateFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")
        val date: Date? = simpleDateFormat.parse(this)

        val outputFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())
        date?.let { outputFormat.format(it) } ?: "Invalid date"
    }
}
@RequiresApi(Build.VERSION_CODES.O)
fun String.toFormattedDate(): LocalDate {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val formatter = DateTimeFormatter.ISO_INSTANT
        val instant = Instant.parse(this)
        instant.atZone(ZoneId.systemDefault()).toLocalDate()
    } else {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        simpleDateFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")
        val date: Date? = simpleDateFormat.parse(this)
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val formattedDate = date?.let { outputFormat.format(it) } ?: "Invalid date"

        SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).parse(formattedDate).toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDate()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateRange(startDate: String, endDate: String): String {
    val startLocalDate = startDate.toFormattedDate()
    val endLocalDate = endDate.toFormattedDate()

    return if (startLocalDate.month == endLocalDate.month && startLocalDate.year == endLocalDate.year) {
        "${startLocalDate.dayOfMonth}-${endLocalDate.dayOfMonth} ${startLocalDate.month.getDisplayName(
            java.time.format.TextStyle.FULL, Locale.getDefault()
        )} ${startLocalDate.year}"
    } else {
        "${startLocalDate.dayOfMonth} ${startLocalDate.month.getDisplayName(
            java.time.format.TextStyle.FULL, Locale.getDefault()
        )} ${startLocalDate.year} - ${endLocalDate.dayOfMonth} ${endLocalDate.month.getDisplayName(
            java.time.format.TextStyle.FULL, Locale.getDefault()
        )} ${endLocalDate.year}"
    }
}

