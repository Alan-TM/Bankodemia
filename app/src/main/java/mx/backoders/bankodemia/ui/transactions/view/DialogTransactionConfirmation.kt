package mx.backoders.bankodemia.ui.transactions.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.databinding.AlertdialogTransactionsBinding

class DialogTransactionConfirmation : DialogFragment() {
    lateinit var binding: AlertdialogTransactionsBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AlertdialogTransactionsBinding.inflate(this.layoutInflater)

        initializeUI()

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setView(binding.root).create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun initializeUI(){
        binding.buttonAlertDialogLayout.setOnClickListener {
            findNavController().navigate(R.id.action_dialogTransactionConfirmation_to_fragmentProcessingTransaction)
        }
    }
}