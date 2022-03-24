package mx.backoders.bankodemia.common.utils

import android.content.Context
import mx.backoders.bankodemia.R

fun errorMessageSelectorByCode(context: Context, httpCode: Int): String{
    return when(httpCode){
        400 -> context.getString(R.string.error_bad_request)
        401 -> context.getString(R.string.error_no_authorized)
        402, 412 -> context.getString(R.string.error_no_money)
        else -> context.getString(R.string.error_no_internet)
    }
}