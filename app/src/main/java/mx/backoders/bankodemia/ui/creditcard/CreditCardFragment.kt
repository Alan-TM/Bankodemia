package mx.backoders.bankodemia.ui.creditcard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import mx.backoders.bankodemia.databinding.FragmentCreditCardBinding
import mx.backoders.bankodemia.ui.home.viewmodel.HomeViewModel

class CreditCardFragment : Fragment() {

    private var _binding: FragmentCreditCardBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreditCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
    }

    private fun initializeObservers() {
        homeViewModel.userProfileResponse.observe(viewLifecycleOwner){
            binding.tvCardNumberFragmentCardNumber.text = it.data.user.id.substring(0..15)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}