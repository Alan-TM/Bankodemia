package mx.backoders.bankodemia.ui.login.view

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.common.utils.*
import mx.backoders.bankodemia.databinding.ActivityWelcomeBinding
import mx.backoders.bankodemia.databinding.FragmentLoginBinding
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel
import mx.backoders.bankodemia.ui.main.HomeActivity

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    val loginViewModel: LoginViewModel by viewModels()
    lateinit var shared: SharedPreferencesInstance

    private lateinit var tietEmail: TextInputEditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tietPassword: TextInputEditText
    private lateinit var tilPassword: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
        loginObservers()
        isStillValidToken()
    }

    private fun isStillValidToken() {
        loginViewModel
    }

    private fun initializeUI() {
        shared = SharedPreferencesInstance.getInstance(requireActivity().applicationContext)
        binding.progressBarLogin.isVisible = false

        //Adding Listeners
        tietEmail = binding.loginEdittextEmailTiet
        tilEmail = binding.loginEdittextEmailTil
        addIsEmailCorrectListener(requireActivity().getApplicationContext(), tietEmail, tilEmail)

        tietPassword = binding.loginEdittextPasswordTiet
        tilPassword = binding.loginEdittextPasswordTil
        addIsEmptyChecker(requireActivity().getApplicationContext(), tietPassword, tilPassword)

        binding.returnLogin.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.loginLoginButton.setOnClickListener {
            if (!checkForInternet(requireActivity().getApplicationContext())) {
                showSnack(binding.root, getString(R.string.error_no_internet), Snackbar.LENGTH_INDEFINITE)
            } else {
                if (!isEmpty(
                        requireActivity().getApplicationContext(),
                        tietPassword,
                        tilPassword
                    )
                ) {
                    startLogIn()
                }
            }
        }
    }

    private fun startLogIn() {
        isEmailCorrect(requireActivity().getApplicationContext(), tietEmail, tilEmail)
        isEmpty(requireActivity().getApplicationContext(), tietPassword, tilPassword)

        if (!tilEmail.isErrorEnabled && !tilPassword.isErrorEnabled) {
            val email = tietEmail.text.toString()
            val pass = tietPassword.text.toString()
//            binding.progressBarLogin.isVisible = true
            loginViewModel.getLogin("1m",LoginDto(email, pass))
        } else {
            when {
                tietEmail.text!!.isEmpty() -> tilEmail.requestFocus()
                tietPassword.text!!.isEmpty() -> tilPassword.requestFocus()
            }
        }
    }

    private fun loginObservers() {
        loginViewModel.login.observe(viewLifecycleOwner) { login ->
            shared.saveSession(login)
        }
        loginViewModel.tokenExpired.observe(viewLifecycleOwner) { tokenExpirado ->
            if (tokenExpirado) {
                // regresarlo al login
            }
        }
        loginViewModel.success.observe(viewLifecycleOwner) { success ->
            if (success) {
                openHomeActivity()
            } else {
                showErrorMessage()
            }
        }
        loginViewModel.isLoading.observe(viewLifecycleOwner){isLoading ->
            binding.progressBarLogin.isVisible = isLoading
        }
    }

    private fun showErrorMessage() {
        binding.loginEdittextEmailTiet.error = getString(R.string.error_login)
        binding.loginEdittextPasswordTil.error = getString(R.string.error_login)
        showSnack(binding.root, getString(R.string.error_login), Snackbar.LENGTH_LONG)
    }

    fun openHomeActivity() {
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
    }
}

