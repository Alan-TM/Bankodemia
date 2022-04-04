package mx.backoders.bankodemia.ui.transactions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.databinding.FragmentAddAccountEndBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel
import mx.backoders.bankodemia.ui.transactions.viewmodel.AddContactViewModel

class AddContactComplete : Fragment() {
    private var _binding: FragmentAddAccountEndBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()

    private val addContactViewModel: AddContactViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddAccountEndBinding.inflate(inflater, container, false)
        homeViewModel.setOnBackPressedEnable(false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        addContactViewModel.sendContactDto()
        initializeObservers()
    }

    private fun initializeObservers() {
        with(addContactViewModel){
            isLoading.observe(viewLifecycleOwner){
                binding.addcontactCompleteButton.isEnabled = !it
            }
        }
    }

    private fun initializeUI() {
        binding.addcontactCompleteButton.setOnClickListener {
            findNavController().navigate(R.id.action_addContactComplete_to_contactListFragment)
            homeViewModel.setOnBackPressedEnable(true)
        }
    }


}