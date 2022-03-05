package mx.backoders.bankodemia

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork

class HomeViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val userProfileResponse = MutableLiveData<UserFullProfileResponse>()
    val error = MutableLiveData<String>()
    private val serviceNetwork = ServiceNetwork()

    fun getUserProfile(){
        viewModelScope.launch {
            isLoading.postValue(true)
            try{
                val response = serviceNetwork.getUserProfile()

                if(response.isSuccessful){
                    userProfileResponse.postValue(response.body())

                    Log.e("PROFILE", response.body().toString())
                } else if(response.code() == 401){
                    Log.e("PROFILE", "required!!")
                }
            } catch(e: Exception){
                error.postValue(e.message)
            }
        }
    }
}