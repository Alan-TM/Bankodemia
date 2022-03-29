package mx.backoders.bankodemia.ui.login.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.common.utils.*
import mx.backoders.bankodemia.databinding.ActivityWelcomeBinding
import mx.backoders.bankodemia.databinding.FragmentLoginBinding
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel
import mx.backoders.bankodemia.ui.main.HomeActivity

class LoginFragment : Fragment() {

    lateinit var shared: SharedPreferencesInstance
    private var _bindingWelcomeActivity: ActivityWelcomeBinding? = null
    private val bindingWelcomeActivity get() = _bindingWelcomeActivity!!
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var tietEmail: TextInputEditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tietPassword: TextInputEditText
    private lateinit var tilPassword: TextInputLayout
    private lateinit var appContext: Context
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        _bindingWelcomeActivity = ActivityWelcomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //Obtiene contexto.
        appContext = requireContext().applicationContext
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        loginObservers()
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
            if(success){
                logi("Robe send Actividiti:" )
                openHomeActivity()
            }else{
                logi("Robe dont sent activitu ERROR" )
                showErrorMessage()
            }
        }
    }

    private fun initComponents() {

        shared = SharedPreferencesInstance.getInstance(requireActivity().getApplicationContext())
        binding.btnFrgLoginLogin.setOnClickListener {
            startLogIn()
        }

//         Adding Listeners
        tietEmail = binding.tietLoginEmail
        tilEmail = binding.tilLoginEmail
        addIsEmailCorrectListener(requireActivity().getApplicationContext(), tietEmail, tilEmail)

        tietPassword = binding.tietLoginPassword
        tilPassword = binding.tilLoginPassword
        addIsEmptyChecker(requireActivity().getApplicationContext(), tietPassword, tilPassword)
    }

    private fun startLogIn() {
        isEmailCorrect(requireActivity().getApplicationContext(), tietEmail, tilEmail)
        isEmpty(requireActivity().getApplicationContext(), tietPassword, tilPassword)

        if (!tilEmail.isErrorEnabled && !tilPassword.isErrorEnabled) {
            val email = tietEmail.text.toString()
            val pass = tietPassword.text.toString()
            loginViewModel.login(LoginDto(email, pass))
        } else {
            when {
                tietEmail.text!!.isEmpty() -> tilEmail.requestFocus()
                tietPassword.text!!.isEmpty() -> tilPassword.requestFocus()
            }
        }
    }

    private fun showErrorMessage() {
        Toast.makeText(appContext, "ERROR CREDENTIALS", Toast.LENGTH_LONG).show()
    }

    fun openHomeActivity() {
        val intent = Intent(activity, HomeActivity::class.java)
        startActivity(intent)
    }

}

