package mx.backoders.bankodemia.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ServicesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Services Fragment"
    }
    val text: LiveData<String> = _text
}