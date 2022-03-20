package mx.backoders.bankodemia.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import mx.backoders.bankodemia.common.utils.logi
import mx.backoders.bankodemia.databinding.ActivityWelcomeBinding
import mx.backoders.bankodemia.ui.login.view.LoginFragment
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel


class WelcomeActivity : AppCompatActivity() {

    private lateinit var bindingWelcomeActivity: ActivityWelcomeBinding
    lateinit var shared: SharedPreferencesInstance
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingWelcomeActivity = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(bindingWelcomeActivity.root)
        initComponents()
    }

    private fun initComponents() {

        loginObservers()
        bindingWelcomeActivity.btnActLoginLogin.setOnClickListener {
            loginViewModel.welcomeContainerIsVisible(true)

        }
        bindingWelcomeActivity.btnActLoginBack.setOnClickListener {
            loginViewModel.welcomeContainerIsVisible(false)

        }

        bindingWelcomeActivity.btnActLoginSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.welcomeContainer, LoginFragment())
            commit()
        }
    }


    private fun loginObservers() {
        logi("Robe: Entro MAIN loginObservers")
//        loginViewModel.success.observe(this) {
//            if (loginViewModel.success.value == true) {
//                logi("Robe: Entro al chidoo")
//            } else {
//                logi("Robe: Entro al chafaaa")
//            }
//        }
        loginViewModel.liveDataWelcomeContainer.observe(this) { wecomeContainerIsVisible ->
            if (wecomeContainerIsVisible) {
                showLoginFragment()
            } else {
                hideLoginFragment()
            }
        }

        loginViewModel.login.observe(this) { login ->
            shared.saveSession(login)
        }

        // DOES NOT WORK
        loginViewModel.success.observe(this) { success ->
            if(success){
                logi("Robe send Actividiti:" )
            }else{
                logi("Robe dont sent activitu ERROR" )
            }
        }

        loginViewModel.tokenExpired.observe(this) { tokenExpirado ->
            if (tokenExpirado) {
                // regresarlo al login
            }
        }
    }

    private fun showLoginFragment() {
        bindingWelcomeActivity.btnActLoginBack.visibility = View.VISIBLE
        bindingWelcomeActivity.btnActLoginLogin.visibility = View.GONE
        bindingWelcomeActivity.btnActLoginSignup.visibility = View.GONE
        bindingWelcomeActivity.welcomeContainer.visibility = View.VISIBLE
    }

    private fun hideLoginFragment() {
        bindingWelcomeActivity.btnActLoginBack.visibility = View.GONE
        bindingWelcomeActivity.btnActLoginLogin.visibility = View.VISIBLE
        bindingWelcomeActivity.btnActLoginSignup.visibility = View.VISIBLE
        bindingWelcomeActivity.welcomeContainer.visibility = View.GONE
    }
}
