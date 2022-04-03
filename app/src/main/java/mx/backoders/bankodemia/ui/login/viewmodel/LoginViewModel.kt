package mx.backoders.bankodemia.ui.login.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import mx.backoders.bankodemia.common.utils.logi
import java.io.IOException
import android.widget.Toast

const val EXTRA_MESSAGE = "mx.backoders.bankodemia.ui.main.HomeActivity.MESSAGE"

class LoginViewModel : ViewModel() {
    //llamamos al servicio
    private val service = ServiceNetwork()
    val login = MutableLiveData<UserLoginResponse>()
    private var error = MutableLiveData<String>()
    var isLoading = MutableLiveData<Boolean>()
    val tokenExpired = MutableLiveData<Boolean>()

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    fun getLogin(expires_in: String, dto: LoginDto) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val responseLogin = service.getLogin(expires_in, dto)
                if (responseLogin.isSuccessful) {
                    login.postValue(responseLogin.body())
                    tokenExpired.postValue(false)
                    _success.value = true
                } else if (responseLogin.code() == 401) {
                    tokenExpired.postValue(true)
                    _success.value = false
                } else {
                    error.postValue(responseLogin.errorBody().toString())
                }
            } catch (err: IOException) {
                error.postValue(err.localizedMessage)
            }
            isLoading.postValue(false)
        }
    }

    fun isStillValidToken() {
        viewModelScope.launch {
            try {
            } catch (err: IOException) {
                error.postValue(err.localizedMessage)
            }

        }

    }


}