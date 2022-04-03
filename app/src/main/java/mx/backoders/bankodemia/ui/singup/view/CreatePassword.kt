package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.checkForInternet
import mx.backoders.bankodemia.common.utils.isEmpty
import mx.backoders.bankodemia.common.utils.showSnack
import mx.backoders.bankodemia.common.utils.textFieldsValidator
import mx.backoders.bankodemia.databinding.FragmentCreatePasswordBinding
import mx.backoders.bankodemia.ui.singup.viewmodel.RegisterPasswordViewModel
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel

class CreatePassword : Fragment() {

    private var _binding: FragmentCreatePasswordBinding? = null
    private val binding get() = _binding!!

    private val registerPasswordBinding: RegisterPasswordViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }

    private fun initializeUI() {
        with(binding) {
            createpasswordCreatepasswordButton.setOnClickListener {
                startEmptyPasswordChecker()
                if (!checkForInternet(requireActivity().getApplicationContext())) {
                    showSnack(binding.root, getString(R.string.error_no_internet), Snackbar.LENGTH_INDEFINITE)
                } else {
                    if (textFieldsValidator(
                            createpasswordEdittextPasswordTil,
                            createpasswordEdittextConfirmpasswordTil))
                        findNavController().navigate(R.id.action_create_Password_to_sendYourDates)
                }
            }

            createpasswordEdittextPasswordTil.editText?.addTextChangedListener { password ->
                consecutiveCharactersValidatorHelper(password.toString())
            }

            createpasswordEdittextConfirmpasswordTil.editText?.addTextChangedListener { password_confirm ->
                if (registerPasswordBinding.isSamePassword(
                        createpasswordEdittextPasswordTiet.text.toString(),
                        password_confirm.toString()
                    )
                ) {
                    createpasswordEdittextConfirmpasswordTil.isErrorEnabled = false
                } else {
                    createpasswordEdittextConfirmpasswordTil.error =
                        getString(R.string.error_matching_password)
                }
            }
            returnLogin.setOnClickListener {
                it.findNavController().navigateUp()
            }
        }
    }

    private fun consecutiveCharactersValidatorHelper(password: String) {
        if (!registerPasswordBinding.consecutiveNumbersPassword(password))
            binding.createpasswordEdittextPasswordTil.error =
                getString(R.string.error_consecutive_numbers)
        else if (!registerPasswordBinding.consecutiveCharacterPassword(password))
            binding.createpasswordEdittextPasswordTil.error =
                getString(R.string.error_consecutive_characters)
        else
            binding.createpasswordEdittextPasswordTil.isErrorEnabled = false
    }

    private fun startEmptyPasswordChecker() {
        with(binding) {
            isEmpty(
                requireContext(),
                createpasswordEdittextPasswordTiet,
                createpasswordEdittextPasswordTil
            )

            isEmpty(
                requireContext(),
                createpasswordEdittextConfirmpasswordTiet,
                createpasswordEdittextConfirmpasswordTil
            )
        }
    }

}