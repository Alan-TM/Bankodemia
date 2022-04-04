package mx.backoders.bankodemia.common.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar.*
import mx.backoders.bankodemia.R

fun errorMessageSelectorByCode(context: Context, httpCode: Int): String{
    return when(httpCode){
        400 -> context.getString(R.string.error_bad_request)
        401 -> context.getString(R.string.error_no_authorized)
        402, 412 -> context.getString(R.string.error_no_money)
        else -> context.getString(R.string.error_no_internet)
    }
}

class ErrorManager(){
    operator fun invoke(view: View, errorCode: Int){
        when(errorCode){
            400 -> showSnack(view, view.context.getString(R.string.error_bad_request), LENGTH_INDEFINITE)
            401 -> sendUserToLogin()
            402, 412 -> sendUserToMakeTransaction()
            else -> sendMessage(view.context, view.context.getString(R.string.error_something_happened))
        }
    }

    private fun sendUserToMakeTransaction() {
        TODO("Not yet implemented")
    }

    private fun sendUserToLogin() {
        TODO("Not yet implemented")
    }

    private fun sendMessage(context: Context, text: String){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}