package mx.backoders.bankodemia.ui.main.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.utils.CountType
import mx.backoders.bankodemia.common.utils.addIsEmptyChecker
import mx.backoders.bankodemia.common.utils.addLengthChecker
import mx.backoders.bankodemia.common.utils.isEmailCorrect
import mx.backoders.bankodemia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exampleCheckErrors()
    }

    private fun exampleCheckErrors() {
        addIsEmptyChecker(applicationContext,binding.tietEmpty,binding.tilEmpty)
        binding.tietLength.setOnFocusChangeListener(this)
        isEmailCorrect(applicationContext,binding.tietEmail,binding.tilEmail, findViewById(R.id.tiet_empty))
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
        if (!hasFocus) {
            addLengthChecker(applicationContext,binding.tietLength,binding.tilLength, CountType.CARD.length)
        }
    }
}