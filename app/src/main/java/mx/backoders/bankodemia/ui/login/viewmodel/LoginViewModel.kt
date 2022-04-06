package mx.backoders.bankodemia.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import java.io.IOException

const val EXTRA_MESSAGE = "mx.backoders.bankodemia.ui.main.HomeActivity.MESSAGE"

class LoginViewModel : ViewModel() {
    //llamamos al servicio
    private val service = ServiceNetwork()
    val login = MutableLiveData<UserLoginResponse>()
    private var error = MutableLiveData<String>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isTokenExpired = MutableLiveData<Boolean>()
    val isTokenExpired: LiveData<Boolean> = _isTokenExpired


    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> get() = _success

    fun getLogin(expires_in: String, dto: LoginDto) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val responseLogin = service.getLogin(expires_in, dto)
                if (responseLogin.isSuccessful) {
                    login.postValue(responseLogin.body())
                    _success.value = true
                } else if (responseLogin.code() == 401) {
                    _success.value = false
                } else {
                    error.postValue(responseLogin.errorBody().toString())
                }
            } catch (err: IOException) {
                error.postValue(err.localizedMessage)
            }
            _isLoading.postValue(false)
        }
    }

    fun isStillValidToken() {
        viewModelScope.launch {
            try {
                val response = service.getAllUsers()
                _isTokenExpired.value = !response.isSuccessful
            } catch (e: IOException) {
                //TODO add io exception
            }
        }
    }


}