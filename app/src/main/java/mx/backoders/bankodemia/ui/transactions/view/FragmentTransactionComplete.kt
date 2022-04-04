package mx.backoders.bankodemia.ui.transactions.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.databinding.FragmentTransactionCompletedBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel

class FragmentTransactionComplete : Fragment() {

    private var _binding: FragmentTransactionCompletedBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionCompletedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.setOnBackPressedEnable(true)
        initializeUI()
    }

    private fun initializeUI() {
        binding.buttonBackFragmentTransactionComplete.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentTransactionComplete_to_nav_home)
        }
    }
}