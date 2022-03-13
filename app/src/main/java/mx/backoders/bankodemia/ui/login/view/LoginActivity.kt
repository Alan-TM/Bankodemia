package mx.backoders.bankodemia.ui.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.databinding.ActivityLoginBinding
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    val loginViewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding
    lateinit var shared : SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initComponents()
        loginObservers()
    }

    private fun loginObservers() {
        loginViewModel.login.observe(this) { login ->
            shared.saveSession(login)
//            shared.saveToken(it.access_token)
        }
        loginViewModel.tokenExpired.observe(this){tokenExpirado ->
            if(tokenExpirado){
                // regresarlo al login
            }
        }
    }

    private fun initComponents() {
        // shared
        shared = SharedPreferencesInstance.getInstance(this)
        // binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        loginViewModel.onCreate(context = this)

        binding.buttonIniciarSesion.setOnClickListener {
            startLogIn()
        }
    }

    private fun startLogIn() {
//        if (checkEmptyOrErrorFields(applicationContext, til_correo, til_password)) {
//            loginViewModel.getLogin()
//        } else {
//            if (tiet_correo.text!!.isEmpty() && tiet_password.text!!.isEmpty())
//                til_correo.requestFocus()
//            else if (tiet_password.text!!.isEmpty())
//                til_password.requestFocus()
//            else
//                til_correo.requestFocus()
//        }
    }


}