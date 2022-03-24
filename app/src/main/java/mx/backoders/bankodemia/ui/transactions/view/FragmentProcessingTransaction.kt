package mx.backoders.bankodemia.ui.transactions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import mx.backoders.bankodemia.databinding.FragmentProcessingTransactionBinding
import mx.backoders.bankodemia.ui.transactions.viewmodel.TransactionsViewModel

class FragmentProcessingTransaction : Fragment() {

    private var _binding: FragmentProcessingTransactionBinding? = null
    private val binding get() = _binding!!

    private val transactionViewModel : TransactionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProcessingTransactionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        //initializeTransaction()
        initializeObservers()
    }

    private fun initializeObservers() {
        //see loading state
    }

    private fun initializeTransaction(){
        transactionViewModel.makeTransaction()
    }
}