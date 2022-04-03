package mx.backoders.bankodemia.ui.singup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.checkForInternet
import mx.backoders.bankodemia.common.utils.isEmpty
import mx.backoders.bankodemia.common.utils.showSnack
import mx.backoders.bankodemia.common.utils.PasswordError
import mx.backoders.bankodemia.common.utils.PasswordError.*
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
    private lateinit var passwordTextField: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBackPressedCallbackHandler()
            }
        })
    }

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
                passwordTextField = createpasswordEdittextPasswordTiet.text.toString()
                val passwordConfirm = createpasswordEdittextConfirmpasswordTiet.text.toString()
                if (flagPasswordError == NONE || flagPasswordConfirmError == NONE) {
                    registerPasswordViewModel.isEmptyPassword(passwordTextField)
                    registerPasswordViewModel.isEmptyPasswordConfirmation(passwordConfirm)
                }
                if (flagPasswordError == NONE)
                    registerPasswordViewModel.minLengthPassword(passwordTextField)
                if (flagPasswordError == NONE)
                    registerPasswordViewModel.isValidConsecutivePassword(passwordTextField)
                if (flagPasswordError == NONE)
                    registerPasswordViewModel.isValidRepeatedCharacters(passwordTextField)
                if (flagPasswordError == NONE && flagPasswordConfirmError == NONE)
                    registerPasswordViewModel.isSamePassword(passwordTextField, passwordConfirm)

                if (textFieldsValidator(
                        createpasswordEdittextPasswordTil,
                        createpasswordEdittextConfirmpasswordTil
                    )
                ) {
                    findNavController().navigate(R.id.action_create_Password_to_sendYourDates)
                    signUpViewModel.setUserPassword(passwordTextField)
                }

            }
        }

        createpasswordEdittextPasswordTil.editText?.addTextChangedListener { password ->
            passwordTextField = password.toString()
            registerPasswordViewModel.isValidConsecutivePassword(password.toString())
        }

        createpasswordEdittextConfirmpasswordTil.editText?.addTextChangedListener { password_confirm ->
            registerPasswordViewModel.isSamePassword(
                createpasswordEdittextPasswordTiet.text.toString(),
                password_confirm.toString()
            )
        }

        returnLogin.setOnClickListener {
            onBackPressedCallbackHandler()
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
                    ) {
                    createpasswordEdittextConfirmpasswordTil.isErrorEnabled = false
                }
                else {
                    createpasswordEdittextConfirmpasswordTil.error =
                        getString(R.string.error_matching_password)
                }
            }
        }
    }

    private fun onBackPressedCallbackHandler() {
        findNavController().navigateUp()
        if (passwordTextField.isNotEmpty() && passwordTextField.isNotBlank())
            signUpViewModel.setUserPassword(passwordTextField)
    }

    override fun onStop() {
        super.onStop()
        registerPasswordViewModel.clearMediators()
        signUpViewModel.setUserPassword(passwordTextField)
    }
}