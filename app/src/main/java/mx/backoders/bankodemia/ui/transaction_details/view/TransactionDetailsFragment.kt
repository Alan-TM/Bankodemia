package mx.backoders.bankodemia.ui.transaction_details.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.model.Transactions.Transaction
import mx.backoders.bankodemia.common.utils.currencyParser
import mx.backoders.bankodemia.common.utils.timeParserForDetailsView
import mx.backoders.bankodemia.databinding.FragmentTransactionDetailsBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transaction_details.viewmodel.TransactionDetailsViewModel

@RequiresApi(Build.VERSION_CODES.O)
class TransactionDetailsFragment : Fragment() {

    private lateinit var transactionID: String

    private val homeViewModel: HomeViewModel by activityViewModels()
    private val transactionDetailsViewModel: TransactionDetailsViewModel by viewModels()

    private var _binding : FragmentTransactionDetailsBinding? = null
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
    ): View {
        _binding = FragmentTransactionDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        transactionDetailsViewModel.fetchTransactionData(transactionID)

        initializeObservers()
        initializeUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initializeObservers(){
        transactionDetailsViewModel.transactionDetailsResponse.observe(viewLifecycleOwner){
            setView(it.data.transaction)
        }

        transactionDetailsViewModel.isLoading.observe(viewLifecycleOwner, ::loadingIndicator)
    }

    private fun loadingIndicator(visibility: Boolean) {
//        binding.transactionDetailsLoadingContainer.isVisible = visibility
//        binding.transactionDetailsDetailsContainer.isVisible = !visibility
    }

    private fun setView(transaction: Transaction) {
        with(binding){
            transactionDetailsAmountTextView.text = currencyParser(transaction.amount)
            transactionDetailsConceptSubtitleTextView.text = transaction.concept
            transactionDetailsConceptTextView.text = transaction.concept
            transactionDetailsDateTextView.text = timeParserForDetailsView(transaction.createdAt)
            transactionDetailsIdTextView.text = transaction._id
            transactionDetailsAccountNumberTextView.text = transaction.issuer.id
        }
    }

    private fun initializeUI(){
        binding.imageViewBackFragmentTransactionDetails.setOnClickListener {
            findNavController().navigateUp()
        }
        setupVisibilityComponents()
    }

    private fun setupVisibilityComponents(){
        homeViewModel.bottomNavIsVisible(false)
    }

}