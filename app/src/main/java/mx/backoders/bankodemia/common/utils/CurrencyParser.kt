package mx.backoders.bankodemia.common.utils

import java.text.NumberFormat
import java.util.*

fun currencyParser(amountToParse: Double): String{
    val currencyFormatter = NumberFormat.getCurrencyInstance()
    currencyFormatter.maximumFractionDigits = 2
    currencyFormatter.currency = Currency.getInstance("USD")

    return currencyFormatter.format(amountToParse)
}