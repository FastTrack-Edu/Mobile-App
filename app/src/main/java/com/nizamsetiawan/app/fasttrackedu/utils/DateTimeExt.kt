package com.nizamsetiawan.app.fasttrackedu.utils
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String.toTime(): String {
    val formatter = DateTimeFormatter.ISO_INSTANT
    val instant = Instant.from(formatter.parse(this))
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    return DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss").format(localDateTime)
}
