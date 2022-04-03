package mx.backoders.bankodemia.ui.home.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance


class SignOut : DialogFragment() {
    lateinit var shared: SharedPreferencesInstance

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            shared = SharedPreferencesInstance.getInstance(requireActivity().applicationContext)
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.close_sesion)
                .setPositiveButton(R.string.aceeptSting_button_alertdialogLayout,
                    DialogInterface.OnClickListener { dialog, id ->
//                        shared.clearAllPreferences()
                        it.finish()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->

                    })

            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}