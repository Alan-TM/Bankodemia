package mx.backoders.bankodemia.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.R

enum class CountType(val length: Int) {
    CARD(16),
    CLABE(18)
}
//enum class Day(val dayOfWeek: Int) {
//    MONDAY(1),
//    TUESDAY(2),
//    WEDNESDAY(3),
//    THURSDAY(4),
//    FRIDAY(5),
//    SATURDAY(6),
//    SUNDAY(7)
//}

//fun main() {
//    for (day in DAY.values())
//        println("[${day.ordinal}] -> ${day.name} (${day.dayOfWeek}^ day of the week)")
//}

fun logi(text:String){
    Log.i("TAG", text)
}

/* Example with addTextChangedListener*/
fun addIsEmptyChecker(
    context: Context,
    tiet: TextInputEditText,
    til: TextInputLayout
): Boolean {
    // Is there any error
    var error = true
    tiet.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editText: Editable?) {

            if (tiet.text.toString().trim().isEmpty()) {
                til.error = context.getString(R.string.error_empty)
                error = true
            } else {
                til.isErrorEnabled = false
                error = false
            }
        }
    })
    return error
}

/* Example with lostFocusListener*/
fun addLengthChecker(
    context: Context,
    tiet: TextInputEditText,
    til: TextInputLayout,
    length: Int
): Boolean {
    var error: Boolean
    if (tiet.text.toString().length != length){
        til.error = context.getString(R.string.error_length)
        error = true
    } else {
        til.isErrorEnabled = false
        error = false
    }
    return error
}

/* Intent of doing some isolate function with lostFocusListener*/
fun isEmailCorrect(
    context: Context,
    tiet: TextInputEditText,
    til: TextInputLayout,
    view: View // TODO ROHE Remove
): Boolean {
//   tiet.setOnFocusChangeListener { view, b ->  } // TODO ROHE: remove this Does not work !!
    var error = true
    tiet.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editText: Editable?) {

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(tiet.text.toString()).matches()) {
                til.isErrorEnabled = false
                error = false
            } else {
                til.error = context.getString(R.string.error_invalid_email)
                error = true
            }
        }
    })
    return error
}
