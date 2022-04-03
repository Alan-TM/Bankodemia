package mx.backoders.bankodemia.common.utils

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.R

enum class CountType(val length: Int) {
    CARD(16),
    CLABE(18)
}

enum class Day(val dayOfWeek: Int) {
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6),
    SUNDAY(7)
}

//fun main() {
//    for (day in DAY.values())
//        println("[${day.ordinal}] -> ${day.name} (${day.dayOfWeek}^ day of the week)")
//}

enum class PhoneLenght(val length: Int) {
    UniversalLength(13)
}


fun logi(text: String) {
    Log.i("TAG", text)
}

/* Example with addTextChangedListener*/
fun addIsEmptyChecker(
    context: Context,
    tiet: TextInputEditText,
    til: TextInputLayout
) {
    // Is there any error
    tiet.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editText: Editable?) {

            if (tiet.text.toString().trim().isEmpty()) {
                til.error = context.getString(R.string.error_empty)
                til.isErrorEnabled = true
            } else {
                til.isErrorEnabled = false
            }
        }
    })
}

fun isEmpty(
    context: Context,
    tiet: TextInputEditText,
    til: TextInputLayout
): Boolean {
    var error: Boolean
    if (tiet.text.toString().trim().isEmpty()) {
        til.error = context.getString(R.string.error_empty)
        til.isErrorEnabled = true
        error = true
    } else {
        til.isErrorEnabled = false
        error = false
    }
    return error
}

fun isEmptyTiet(
    context: Context,
    tiet: TextInputEditText
): Boolean {
    var error: Boolean = if (tiet.text.toString().trim().isEmpty()) {
        tiet.setText(context.getString(R.string.error_empty))
        tiet.setTextColor(Color.RED)
        true
    } else {
        false
    }
    return error
}

fun addLengthChecker(
    context: Context,
    tiet: TextInputEditText,
    til: TextInputLayout,
    length: Int
): Boolean {
    var error: Boolean
    if (tiet.text.toString().length != length) {
        til.error = context.getString(R.string.error_length)
        error = true
    } else {
        til.isErrorEnabled = false
        error = false
    }
    return error
}

fun addIsEmailCorrectListener(
    context: Context,
    tiet: TextInputEditText,
    til: TextInputLayout
) {
    tiet.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editText: Editable?) {

            if (android.util.Patterns.EMAIL_ADDRESS.matcher(tiet.text.toString()).matches()) {
                til.isErrorEnabled = false
            } else {
                til.isErrorEnabled = true
                til.error = context.getString(R.string.error_invalid_email)
            }
        }
    })
}

fun isEmailCorrect(
    context: Context,
    tiet: TextInputEditText,
    til: TextInputLayout
): Boolean {
    var error: Boolean
    if (android.util.Patterns.EMAIL_ADDRESS.matcher(tiet.text.toString()).matches()) {
        til.isErrorEnabled = false
        error= false
    } else {
        til.isErrorEnabled = true
        til.error = context.getString(R.string.error_invalid_email)
        error= true
    }
    return error
}

fun textFieldsValidator(vararg tils: TextInputLayout): Boolean{
    var count = 0
    for(item in tils){
        if(item.isErrorEnabled)
            count++
    }
    return count == 0
}

fun showSnack(view: View, message: String, actionMessage:String? = null, onAction: (()-> Unit)? = null) {
    val snack = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)

    if(actionMessage != null && onAction != null){
        snack.setAction(actionMessage){
            onAction()
            snack.dismiss()
        }
    } else {
        snack.setAction(view.context.getString(R.string.ok)){
            snack.dismiss()
        }
    }
    snack.show()
}
