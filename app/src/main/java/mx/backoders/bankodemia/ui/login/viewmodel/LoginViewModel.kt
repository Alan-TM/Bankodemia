package mx.backoders.bankodemia.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import mx.backoders.bankodemia.common.utils.logi
import java.io.IOException

class LoginViewModel : ViewModel() {
    //llamamos al servicio
    private val service = ServiceNetwork()

    val login = MutableLiveData<UserLoginResponse>()
    private val error = MutableLiveData<String>()
    private val isLoading = MutableLiveData<Boolean>()
    val tokenExpired = MutableLiveData<Boolean>()


    fun getLogin(expires_in: String, dto: LoginDto) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val responseLogin = service.getLogin(expires_in, dto)
                if (responseLogin.isSuccessful) {
                    login.postValue(responseLogin.body())
                } else if (responseLogin.code() == 401) {
                    tokenExpired.postValue(true)
                } else {
                    error.postValue(responseLogin.errorBody().toString())
                }
            } catch (err: IOException) {
                error.postValue(err.localizedMessage)
            }
            isLoading.postValue(false)
        }
    }

    fun login(dto: LoginDto){
        viewModelScope.launch {
            val response = service.login(dto)
            if(response.isSuccessful){
                login.postValue(response.body())
                logi("Robe: Correct Login")
            }else if (response.code() == 401)
                tokenExpired.postValue(true)
        }
    }
}