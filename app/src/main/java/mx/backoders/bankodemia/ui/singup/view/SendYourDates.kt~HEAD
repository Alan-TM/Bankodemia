package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.ErrorManager
import mx.backoders.bankodemia.common.utils.checkForInternet
import mx.backoders.bankodemia.common.utils.showSnack
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
        if (!checkForInternet(requireActivity().applicationContext)) {
            showSnack(
                binding.root,
                getString(R.string.error_no_internet),
                Snackbar.LENGTH_INDEFINITE,
                getString(R.string.accept)
            ) {
                findNavController().navigateUp()
            }
        }else{
            errorManager = ErrorManager(view)
            errorManager.isSignUpErrorManagerEnabled(true)
            signUpViewModel.setOnBackPressedEnable(false)
            signUpViewModel.makeSignUpCall()
            initializeObservers()
        }
    }

    private fun initializeObservers() {
        with(signUpViewModel) {
            isLoading.observe(viewLifecycleOwner) {
                if (!it) {
                    findNavController().navigate(R.id.action_sendYourDates_to_confirmation)
                    signUpViewModel.setOnBackPressedEnable(true)
                }
            }

            errorResponse.observe(viewLifecycleOwner) {
                transactionError(it)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        signUpViewModel.errorResponse.removeObservers(this)
    }

    private fun transactionError(code: Int) {
        errorManager(code)
        signUpViewModel.setOnBackPressedEnable(true)
    }
}