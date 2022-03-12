package mx.backoders.bankodemia.ui.creditcard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreditCardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Tarjeta Fragment"
    }
    val text: LiveData<String> = _text
}