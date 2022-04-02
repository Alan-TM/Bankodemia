package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.PasswordError
import mx.backoders.bankodemia.common.utils.PasswordError.*
import mx.backoders.bankodemia.common.utils.isEmpty
import mx.backoders.bankodemia.common.utils.textFieldsValidator
import mx.backoders.bankodemia.databinding.FragmentCreatePasswordBinding
import mx.backoders.bankodemia.ui.singup.viewmodel.RegisterPasswordViewModel
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel

class CreatePassword : Fragment() {

    private var _binding: FragmentCreatePasswordBinding? = null
    private val binding get() = _binding!!

    private val registerPasswordViewModel: RegisterPasswordViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by activityViewModels()

    private var flagPasswordError: PasswordError = NONE
    private var flagPasswordConfirmError: PasswordError = NONE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreatePasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerPasswordViewModel.setupMediator()
        initializeUI()
        initializeObservers()
    }

    private fun initializeUI() {
        with(binding) {
            createpasswordCreatepasswordButton.setOnClickListener {
                val password = createpasswordEdittextPasswordTiet.text.toString()
                val passwordConfirm = createpasswordEdittextConfirmpasswordTiet.text.toString()
                if (flagPasswordError == NONE || flagPasswordConfirmError == NONE) {
                    registerPasswordViewModel.isEmptyPassword(password)
                    registerPasswordViewModel.isEmptyPasswordConfirmation(passwordConfirm)
                }
                if(flagPasswordError == NONE)
                    registerPasswordViewModel.minLengthPassword(password)
                if(flagPasswordError == NONE)
                    registerPasswordViewModel.isValidConsecutivePassword(password)
                if(flagPasswordError == NONE)
                    registerPasswordViewModel.isValidRepeatedCharacters(password)
                if(flagPasswordError == NONE && flagPasswordConfirmError == NONE)
                    registerPasswordViewModel.isSamePassword(password, passwordConfirm)

                if(textFieldsValidator(createpasswordEdittextPasswordTil, createpasswordEdittextConfirmpasswordTil)) {
                    signUpViewModel.setUserPassword(password)
                    findNavController().navigate(R.id.action_create_Password_to_sendYourDates)
                }

            }

            createpasswordEdittextPasswordTil.editText?.addTextChangedListener { password ->
                registerPasswordViewModel.isValidConsecutivePassword(password.toString())
            }

            createpasswordEdittextConfirmpasswordTil.editText?.addTextChangedListener { password_confirm ->
                registerPasswordViewModel.isSamePassword(
                    createpasswordEdittextPasswordTiet.text.toString(),
                    password_confirm.toString()
                )
            }
        }
    }

    private fun initializeObservers() {
        with(registerPasswordViewModel) {
            mediatorPasswordErrorLiveData.observe(viewLifecycleOwner) {
                flagPasswordError = it
                val passwordTIL = binding.createpasswordEdittextPasswordTil
                when (it) {
                    NONE -> errorEnableHelper(passwordTIL, true)
                    CONSECUTIVE_CHARACTERS -> errorEnableHelper(
                        passwordTIL,
                        false,
                        R.string.error_consecutive_characters
                    )
                    MIN_LENGTH -> errorEnableHelper(passwordTIL, false, R.string.error_min_length_password)
                    REPEATED_CHARACTERS -> errorEnableHelper(passwordTIL, false, R.string.error_repeated_characters)
                    else -> errorEnableHelper(passwordTIL, false, R.string.error_empty)
                }
            }

            mediatorPasswordConfirmErrorLiveData.observe(viewLifecycleOwner) {
                flagPasswordConfirmError = it
                val passwordConfirmTIL = binding.createpasswordEdittextConfirmpasswordTil
                when (it) {
                    NONE -> errorEnableHelper(
                        passwordConfirmTIL,
                        true
                    )
                    NOT_MATCHING -> errorEnableHelper(
                        passwordConfirmTIL,
                        false,
                        R.string.error_matching_password
                    )
                    else -> errorEnableHelper(
                        passwordConfirmTIL,
                        false,
                        R.string.error_empty
                    )
                }
            }
        }
    }

    private fun errorEnableHelper(til: TextInputLayout, isValid: Boolean, string: Int = 0) {
        if (isValid) {
            til.isErrorEnabled = false
        } else {
            til.error = getString(string)
        }
    }
}