package mx.backoders.bankodemia.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.databinding.ActivityWelcomeBinding
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel
import org.json.JSONObject


class WelcomeActivity : AppCompatActivity() {

    private lateinit var bindingWelcomeActivity: ActivityWelcomeBinding
    private val loginViewModel: LoginViewModel by viewModels()
    lateinit var shared: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingWelcomeActivity = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(bindingWelcomeActivity.root)

        shared = SharedPreferencesInstance.getInstance(applicationContext)

//        isStillValidToken()
//        loginObservers()

    }

    private fun isStillValidToken() {
        loginViewModel.isStillValidToken()
    }

    private fun loginObservers() {
        loginViewModel.isTokenExpired.observe(this){isTokenExpired ->
            if(!isTokenExpired){
                openHomeActivity()
            }
        }
    }

    fun openHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

}