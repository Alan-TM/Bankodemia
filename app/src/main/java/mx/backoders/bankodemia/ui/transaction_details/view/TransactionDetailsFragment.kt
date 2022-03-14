package mx.backoders.bankodemia.ui.transaction_details.view

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.model.Transactions.Transaction
import mx.backoders.bankodemia.databinding.TransactionDetailsFragmentBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transaction_details.viewmodel.TransactionDetailsViewModel

@RequiresApi(Build.VERSION_CODES.O)
class TransactionDetailsFragment : Fragment() {

    private lateinit var transactionID: String

    companion object {
        fun newInstance() = TransactionDetailsFragment()
    }

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val transactionDetailsViewModel: TransactionDetailsViewModel by viewModels()

    private var _binding : TransactionDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            transactionID = it.getString("transactionID").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TransactionDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.bottomNavIsVisible(false)

        transactionDetailsViewModel.fetchTransactionData(transactionID)

        initializeObservers()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initializeObservers(){
        transactionDetailsViewModel.transactionDetailsResponse.observe(viewLifecycleOwner){
            setView(it)
        }
    }

    private fun setView(transaction: Transaction) {
        with(binding){
            transactionDetailsAmountTextView.text = transaction.amount.toString()
            transactionDetailsConceptSubtitleTextView.text = transaction.concept
            transactionDetailsConceptTextView.text = transaction.concept
            transactionDetailsDateTextView.text = transaction.createdAt
            transactionDetailsIdTextView.text = transaction._id
            transactionDetailsAccountNumberTextView.text = "?????"
        }
    }

}