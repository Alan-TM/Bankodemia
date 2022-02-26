package mx.backoders.bankodemia.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.model.LoginResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import java.io.IOException

class LoginViewModel: ViewModel() {
    //llamamos al servicio
    private val service = ServiceNetwork()

    val login = MutableLiveData<LoginResponse>()
    val error = MutableLiveData<String>()
    val cargando = MutableLiveData<Boolean>()



    fun getLogin(limit : Int){
        viewModelScope.launch {
            cargando.postValue(true)
            try {
                val responseLogin = service.getLogin(limit)

                if (responseLogin.isSuccessful){
                    login.postValue(responseLogin.body())
                }else {
                    error.postValue(responseLogin.errorBody().toString())
                }
            }catch (err : IOException){
                error.postValue(err.localizedMessage)
            }
        }
    }
}