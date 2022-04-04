package mx.backoders.bankodemia.ui.transactions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.ErrorManager
import mx.backoders.bankodemia.common.utils.checkForInternet
import mx.backoders.bankodemia.common.utils.showSnack
import mx.backoders.bankodemia.databinding.FragmentAddAccountBinding
import mx.backoders.bankodemia.ui.transactions.viewmodel.AddContactViewModel

class AddAccountFragment : Fragment() {

    private var _binding: FragmentAddAccountBinding? = null
    private val binding get() = _binding!!

    private val addContactViewModel: AddContactViewModel by activityViewModels()

    private lateinit var errorManager: ErrorManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        initializeObservers()
    }

    override fun onResume() {
        super.onResume()
        errorManager = ErrorManager(requireView())
        addContactViewModel.getAllUsers()
    }

    private fun initializeUI() {
        with(binding){
            addaccountAddcontactButton.setOnClickListener {
                if (!checkForInternet(requireActivity().applicationContext)) {
                    showSnack(binding.root, getString(R.string.error_no_internet), Snackbar.LENGTH_INDEFINITE)
                } else {
                    validateDropDownOption()
                }
            }

            dropdownUserNames.setOnItemClickListener { _, _, position, _ ->
                addContactViewModel.getUserFromList(position)
            }
        }
    }

    private fun validateDropDownOption() {
        val dropdownText = binding.dropdownUserNames.text.toString()
        val aliasText = binding.addcontactAliasTiet.text.toString()
        if (dropdownText.isNotEmpty() || dropdownText.isNotBlank()) {
            addContactViewModel.setShortNameForContact(aliasText)
            addContactViewModel.makeContactBody()
            findNavController().navigate(R.id.action_addAccountFragment2_to_addContactComplete)
        } else {
            Toast.makeText(requireContext(), getString(R.string.error_select_user), Toast.LENGTH_LONG).show()
        }
    }

    private fun initializeObservers() {
        with(addContactViewModel){
            listForView.observe(viewLifecycleOwner, ::setupDropdown)

            errorResponse.observe(viewLifecycleOwner){
                errorManager(it)
            }
        }
    }

    private fun setupDropdown(names: ArrayList<String>) {
        val arrayAdapterRama =
            ArrayAdapter(
                requireActivity().applicationContext,
                android.R.layout.simple_list_item_1,
                names
            )
        binding.dropdownUserNames.setAdapter(arrayAdapterRama)
    }
}