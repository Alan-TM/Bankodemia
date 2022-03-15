package mx.backoders.bankodemia.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.R
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import mx.backoders.bankodemia.common.utils.logi
import mx.backoders.bankodemia.ui.login.view.LoginFragment
import java.io.IOException

class LoginViewModel : ViewModel() {
    //llamamos al servicio
    private val service = ServiceNetwork()
    var success = MutableLiveData<Boolean>()
    val login = MutableLiveData<UserLoginResponse>()
    private var error = MutableLiveData<String>()
    private var isLoading = MutableLiveData<Boolean>()
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
//        var success:Boolean = false
        viewModelScope.launch {
            val response = service.login(dto)
            if(response.isSuccessful){
                login.postValue(response.body())
                logi("Robe: Correct Login")
                success.postValue(true)
            }else if (response.code() == 401) {
//                tokenExpired.postValue(true)
                success.postValue(false)
            }
        }
//        return success
    }
}