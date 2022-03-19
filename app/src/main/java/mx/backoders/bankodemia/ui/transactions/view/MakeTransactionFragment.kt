package mx.backoders.bankodemia.ui.transactions.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.databinding.FragmentMakeTransactionBinding
import mx.backoders.bankodemia.ui.transactions.viewmodel.TransactionsViewModel

class MakeTransactionFragment : Fragment() {
    private var _binding: FragmentMakeTransactionBinding? = null

    private val binding get() = _binding!!

    private val makeTransactionViewModel: TransactionsViewModel by viewModels()
    private lateinit var contactID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let{
            contactID = it.getString("contactID").toString()
        }
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMakeTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //remove this after
        Log.e("CONTACT_ID", contactID)

        initializeUI()
        initializeObservers()
    }

    private fun initializeObservers() {
        makeTransactionViewModel.transactionResponse.observe(viewLifecycleOwner){
            Toast.makeText(requireContext(), it.data.transaction.concept, Toast.LENGTH_LONG).show()
        }
    }

    private fun initializeUI(){
        binding.makeTransactionBackButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.makeTransactionBackButton.setOnClickListener {
            makeTransactionViewModel.makeTransactionBody(contactID,
                binding.textInputConceptSend.editText?.text.toString(),
                binding.textInputLayoutQunatitySend.editText?.text.toString().toDouble()
            )

            makeTransactionViewModel.makeTransaction()
        }
    }
}