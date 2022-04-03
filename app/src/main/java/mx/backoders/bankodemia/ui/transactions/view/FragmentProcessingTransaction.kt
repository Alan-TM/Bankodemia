package mx.backoders.bankodemia.ui.transactions.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.errorMessageSelectorByCode
import mx.backoders.bankodemia.databinding.FragmentProcessingTransactionBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transactions.viewmodel.TransactionsViewModel

class FragmentProcessingTransaction : Fragment() {

    private var _binding: FragmentProcessingTransactionBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val transactionViewModel : TransactionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel.setOnBackPressedEnable(false)
        _binding = FragmentProcessingTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
        transactionViewModel.makeTransaction()
    }

    private fun initializeObservers() {
        with(transactionViewModel){
            isLoading.observe(viewLifecycleOwner){
                transactionCompleted(it)
            }

            errorResponse.observe(viewLifecycleOwner){ code ->
                if(code != 0) {
                    transactionError(code)
                    setErrorCode(0)
                }
                Log.e("error", code.toString())
            }
        }
    }

    private fun transactionCompleted(isLoading: Boolean){
        if(!isLoading){
            transactionViewModel.clearTransactionBodyStateHandle()
            findNavController().navigate(R.id.action_fragmentProcessingTransaction_to_fragmentTransactionComplete)
        }
    }

    private fun transactionError(code: Int){
        val errorMessage = errorMessageSelectorByCode(requireContext(), code)
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()

        findNavController().navigate(R.id.action_fragmentProcessingTransaction_to_makeTransactionFragment, null)
    }
}