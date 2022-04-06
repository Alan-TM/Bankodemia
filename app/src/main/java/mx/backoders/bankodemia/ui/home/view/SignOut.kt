package mx.backoders.bankodemia.ui.home.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
<<<<<<< HEAD
import android.content.Intent
=======
>>>>>>> develop
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
<<<<<<< HEAD
import mx.backoders.bankodemia.ui.main.WelcomeActivity


class SignOut : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
=======


class SignOut : DialogFragment() {
    lateinit var shared: SharedPreferencesInstance

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            shared = SharedPreferencesInstance.getInstance(requireActivity().applicationContext)
>>>>>>> develop
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.close_sesion)
                .setPositiveButton(R.string.aceeptSting_button_alertdialogLayout,
                    DialogInterface.OnClickListener { dialog, id ->
<<<<<<< HEAD
                        SharedPreferencesInstance.clearAllPreferences()
                        startActivity(
                            Intent(requireContext(), WelcomeActivity::class.java)
                                .addFlags(
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                )
                        )
=======
//                        shared.clearAllPreferences()
                        it.finish()
>>>>>>> develop
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->

                    })

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}