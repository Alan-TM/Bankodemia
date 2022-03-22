package mx.backoders.bankodemia.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import mx.backoders.bankodemia.databinding.ActivityNewAccountBinding
import mx.backoders.bankodemia.ui.login.viewmodel.LoginViewModel

class SignUpActivity: AppCompatActivity() {

    private lateinit var bindingNewAccount: ActivityNewAccountBinding
//    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingNewAccount = ActivityNewAccountBinding.inflate(layoutInflater)
        setContentView(bindingNewAccount.root)
    }

}