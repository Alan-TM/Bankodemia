package mx.backoders.bankodemia.ui.transactions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.ErrorManager
import mx.backoders.bankodemia.databinding.FragmentProcessingTransactionBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transactions.viewmodel.TransactionsViewModel

class FragmentProcessingTransaction : Fragment() {

    private var _binding: FragmentProcessingTransactionBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val transactionViewModel: TransactionsViewModel by activityViewModels()

    private lateinit var errorManager: ErrorManager

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
        errorManager = ErrorManager(view)
        initializeObservers()
        transactionViewModel.makeTransaction()
    }

    private fun initializeObservers() {
        with(transactionViewModel) {
            transactionResponse.observe(viewLifecycleOwner) {
                transactionCompleted()
            }

            errorResponse.observe(viewLifecycleOwner) { code ->
                if (code != 0) {
                    transactionError(code)
                    setErrorCode(0)
                }
            }
        }
    }

    private fun transactionCompleted() {
        transactionViewModel.clearTransactionBodyStateHandle()
        findNavController().navigate(R.id.action_fragmentProcessingTransaction_to_fragmentTransactionComplete)

    }

    private fun transactionError(code: Int) {
        errorManager(code)
        homeViewModel.setOnBackPressedEnable(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}