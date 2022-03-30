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
    private val loginViewModel: LoginViewModel by viewModels()
//    private lateinit var sharedPreferences: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingWelcomeActivity = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(bindingWelcomeActivity.root)
//        initUI()
    }

    private fun initUI() {
//        sharedPreferences = SharedPreferencesInstance.getInstance(applicationContext)
    }
}