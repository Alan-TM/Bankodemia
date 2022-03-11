package mx.backoders.bankodemia.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun timeParser(date: String): String{
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")

    return ZonedDateTime.parse(date).format(timeFormatter)
}