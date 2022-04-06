package mx.backoders.bankodemia.ui.transactions.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.model.contacts.ListMyContactsResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import java.io.IOException

class ContactListViewModel : ViewModel() {
    private val _contactListResponse = MutableLiveData<ListMyContactsResponse>()
    val contactListResponse: LiveData<ListMyContactsResponse> get() = _contactListResponse

    private val _errorResponse = MutableLiveData<Int>()
    val errorResponse: LiveData<Int> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val serviceNetwork = ServiceNetwork()


    fun getContactList(){
        viewModelScope.launch {
            _isLoading.value = true
            try{
                val response = serviceNetwork.getContactList()

                if(response.isSuccessful){
                    _contactListResponse.value = response.body()
                    _isLoading.value = false
                } else{
                    _errorResponse.value = response.code()
                }
            } catch(e: IOException){
                _errorResponse.value = 900
            }
        }
    }
}