package mx.backoders.bankodemia.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_welcome)
    }
}