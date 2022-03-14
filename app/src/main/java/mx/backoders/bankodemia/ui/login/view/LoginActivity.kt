package mx.backoders.bankodemia.ui.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.common.utils.*
import mx.backoders.bankodemia.databinding.ActivityLoginBinding
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var shared: SharedPreferencesInstance
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var tietEmail: TextInputEditText
    private lateinit var tilEmail: TextInputLayout
    private lateinit var tietPassword: TextInputEditText
    private lateinit var tilPassword: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents()
        loginObservers()
    }

    private fun loginObservers() {
        loginViewModel.login.observe(this) { login ->
            shared.saveSession(login)
        }
        loginViewModel.tokenExpired.observe(this) { tokenExpirado ->
            if (tokenExpirado) {
                // regresarlo al login
            }
        }
    }

    private fun initComponents() {
        // Shared
        shared = SharedPreferencesInstance.getInstance(this)

        // Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonIniciarSesion.setOnClickListener {
            startLogIn()
        }

        // Adding Listeners
        tietEmail = binding.textInputTextEmail
        tilEmail = binding.textInputLayoutEmail
        addIsEmailCorrectListener(applicationContext, tietEmail, tilEmail)

        tietPassword = binding.textInputEditTextPassword
        tilPassword = binding.textInputLayoutContrasena
        addIsEmptyChecker(applicationContext, tietPassword, tilPassword)
    }

    private fun startLogIn() {
        isEmailCorrect(applicationContext, tietEmail, tilEmail)
        isEmpty(applicationContext, tietPassword, tilPassword)

        if (!tilEmail.isErrorEnabled && !tilPassword.isErrorEnabled) {
            val email = tietEmail.text.toString()
            val pass = tietPassword.text.toString()
            loginViewModel.login(LoginDto(email, pass))
        } else {
            if (tietEmail.text!!.isEmpty() && tietEmail.text!!.isEmpty())
                tilEmail.requestFocus()
            else if (tietPassword.text!!.isEmpty())
                tilPassword.requestFocus()
            else
                tilEmail.requestFocus()
        }
    }


}