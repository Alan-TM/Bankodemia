package mx.backoders.bankodemia.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import mx.backoders.bankodemia.databinding.ActivityWelcomeBinding
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel
import mx.backoders.bankodemia.ui.singup.viewmodel.SignUpViewModel
import org.json.JSONObject


class WelcomeActivity : AppCompatActivity() {

    private lateinit var bindingWelcomeActivity: ActivityWelcomeBinding
    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
//    private lateinit var sharedPreferences: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingWelcomeActivity = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(bindingWelcomeActivity.root)
    }

//    override fun onResume() {
//        super.onResume()
//    }

}