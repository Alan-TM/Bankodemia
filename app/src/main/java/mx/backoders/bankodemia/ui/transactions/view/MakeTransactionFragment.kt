package mx.backoders.bankodemia.ui.transactions.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.PaymentType
import mx.backoders.bankodemia.databinding.FragmentMakeTransactionBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transactions.viewmodel.TransactionsViewModel

class MakeTransactionFragment : Fragment() {
    private var _binding: FragmentMakeTransactionBinding? = null
    private val binding get() = _binding!!

    private val makeTransactionViewModel: TransactionsViewModel by activityViewModels()
    private val homeViewModel: HomeViewModel by activityViewModels()

    private lateinit var payment: PaymentType

    override fun onCreate(savedInstanceState: Bundle?) {
        arguments?.let {
            payment = it.getSerializable("paymentType") as PaymentType

            makeTransactionViewModel.setContactInformation(
                it.getString("contactID").toString(),
                it.getString("contactFullName").toString(),
                payment
            )
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
        initializeUI()
        initializeObservers()
    }

    private fun initializeObservers() {
        with(makeTransactionViewModel) {
            contactFullName.observe(viewLifecycleOwner) { name ->
                binding.makeTransactionFullNameTextView.text = name
            }

            contactID.observe(viewLifecycleOwner) { id ->
                binding.tvInterbankCodeFragmentMakeTransaction.text = id.substring(0..15)
            }

            transactionBody.observe(viewLifecycleOwner) { transactionBody ->
                if (transactionBody.amount > 0) {
                    binding.textInputLayoutQunatitySend.setText(transactionBody.amount.toString())
                    binding.textInputConceptSend.setText(transactionBody.concept)
                }
            }
        }
    }

    private fun initializeUI() {
        with(binding) {
            makeTransactionBackButton.setOnClickListener {
                makeTransactionViewModel.clearTransactionBodyStateHandle()
                findNavController().navigateUp()
            }

            buttonMakeTransfer.setOnClickListener {
                val amount = textInputLayoutQunatitySend.text.toString()
                val concept = textInputConceptSend.text.toString()

                if (makeTransactionViewModel.validateTextField(amount)) {
                    textInputLayoutQunatitySend.error = null
                    if (makeTransactionViewModel.validateTextField(concept)) {
                        textInputConceptSend.error = null
                        sendDataToMakeTransaction(amount, concept)
                    } else {
                        textInputConceptSend.error = getString(R.string.error_empty)
                    }
                } else {
                    textInputLayoutQunatitySend.error = getString(R.string.error_empty)
                }
            }

            setupVisibilityComponents()
        }
    }

    private fun setupVisibilityComponents(){
        with(homeViewModel){
            bottomNavIsVisible(false)
            topToolbarIsVisible(false)
            setOnBackPressedEnable(true)
        }
    }

    private fun sendDataToMakeTransaction(amount: String, concept: String) {
        makeTransactionViewModel.makeTransactionBody(
            concept,
            amount.toDouble(),
        )

        findNavController().navigate(R.id.action_makeTransactionFragment_to_dialogTransactionConfirmation)
    }
}