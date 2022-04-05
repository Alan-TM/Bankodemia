package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.ErrorManager
import mx.backoders.bankodemia.databinding.FragmentSendYourDatesBinding
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel

class SendYourDates : Fragment() {

    private var _binding: FragmentSendYourDatesBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by activityViewModels()


    private lateinit var errorManager: ErrorManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSendYourDatesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorManager = ErrorManager(view)
        signUpViewModel.setOnBackPressedEnable(false)
        signUpViewModel.makeSignUpCall()
        initializeObservers()
    }

    private fun initializeObservers() {
        with(signUpViewModel) {
            isLoading.observe(viewLifecycleOwner) {
                if (!it) {
                    findNavController().navigate(R.id.action_sendYourDates_to_confirmation)
                    signUpViewModel.setOnBackPressedEnable(true)
                }
            }

            userSignUpResponse.observe(viewLifecycleOwner){
                Toast.makeText(requireContext(), it.data.user.name, Toast.LENGTH_LONG).show()
            }

            errorResponse.observe(viewLifecycleOwner) {
                transactionError(it)
            }
        }
    }

    private fun transactionError(code: Int) {
        errorManager(code)
        signUpViewModel.setOnBackPressedEnable(true)
    }
}