package mx.backoders.bankodemia.ui.home.viewmodel

import android.util.Log
import android.view.SurfaceControl
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.model.Transactions.Transaction
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork

class HomeViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val userProfileResponse = MutableLiveData<UserFullProfileResponse>()
    val error = MutableLiveData<String>()
    private val serviceNetwork = ServiceNetwork()

    private val transactionItems = ArrayList<Transaction>()

    fun getUserProfile(){
        viewModelScope.launch {
            isLoading.postValue(true)
            try{
                val response = serviceNetwork.getUserProfile()

                if(response.isSuccessful){
                    userProfileResponse.postValue(response.body())
                    response.body()!!.data.transactions?.let { transactionItems.addAll(it) }

                    transactionItems.forEach {
                        Log.e("Transaction", it.concept)
                    }

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