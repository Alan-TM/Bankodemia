package mx.backoders.bankodemia.common.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun currencyParser(amountToParse: Double): String{
    val currencyFormatter = NumberFormat.getCurrencyInstance()
    currencyFormatter.maximumFractionDigits = 2
    currencyFormatter.currency = Currency.getInstance("USD")

    return currencyFormatter.format(amountToParse)
}

@RequiresApi(Build.VERSION_CODES.O)
fun timeParser(date: String): String{
    val timeFormatter = DateTimeFormatter.ofPattern("h:mm a")

    return ZonedDateTime.parse(date).format(timeFormatter)
}