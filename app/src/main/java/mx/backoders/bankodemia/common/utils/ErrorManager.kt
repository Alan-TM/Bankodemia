package mx.backoders.bankodemia.common.utils

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar.LENGTH_INDEFINITE
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.ui.main.WelcomeActivity

class ErrorManager(val view: View) {
    private var isSignUp = false

    operator fun invoke(errorCode: Int) {
        when (errorCode) {
            0 -> {}
            400 -> showSnack(
                view,
                view.context.getString(R.string.error_bad_request),
                LENGTH_INDEFINITE
            )
            401 -> sendUserToLogin(view)
            402, 412 -> {
                if (isSignUp) {
                    sendUserToCreatePassword(view)
                } else {
                    sendUserToMakeTransaction(view)
                }
            }
            else -> sendMessage(
                view.context,
                view.context.getString(R.string.error_something_happened)
            )
        }
    }

    fun isSignUpErrorManagerEnabled(isEnabled: Boolean){
        isSignUp = isEnabled
    }

    private fun sendUserToMakeTransaction(view: View) {
        sendMessage(view.context, view.context.getString(R.string.error_no_money))
        view.findNavController().navigate(R.id.action_fragmentProcessingTransaction_to_makeTransactionFragment, null)
    }

    private fun sendUserToLogin(view: View) {
        showSnack(
            view, view.context.getString(R.string.error_no_authorized), LENGTH_INDEFINITE,
            view.context.getString(R.string.accept)
        ) {
            view.context.startActivity(
                Intent(view.context, WelcomeActivity::class.java).addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
            )
            SharedPreferencesInstance.clearAllPreferences()
        }
    }

    private fun sendUserToCreatePassword(view: View){
        showSnack(
            view,
            view.context.getString(R.string.error_user_already_exists),
            LENGTH_INDEFINITE,
            view.context.getString(R.string.accept)
        ){
            view.findNavController().navigateUp()
        }
    }

    private fun sendMessage(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}