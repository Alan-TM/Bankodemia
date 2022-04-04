package mx.backoders.bankodemia.ui.transaction_details.viewmodel

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.model.transactions.TransactionDetailsResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import java.io.IOException

class TransactionDetailsViewModel(stateHandle: SavedStateHandle) : ViewModel() {

    private val serviceNetwork = ServiceNetwork()

    private val _transactionID = stateHandle.getLiveData("transactionID", "")
    val transactionID: LiveData<String> = _transactionID

    private val _transactionDetailsResponse = MutableLiveData<TransactionDetailsResponse>()
    val transactionDetailsResponse: LiveData<TransactionDetailsResponse> get() = _transactionDetailsResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _transactionDetailsError = MutableLiveData<Int>()
    val transactionDetailsError: LiveData<Int> get() = _transactionDetailsError

    fun fetchTransactionData(id: String){
        viewModelScope.launch {
            _isLoading.value = true
            try{
                val response = serviceNetwork.getTransactionDetails(id)

                if(response.isSuccessful){
                    _transactionDetailsResponse.value = response.body()
                    Log.e("TransactionDetails", response.body().toString())
                    _isLoading.value = false
                } else {
                    _transactionDetailsError.value = response.code()
                }
            }catch(e: IOException){
                _transactionDetailsError.value = 900
            }
        }
    }

    fun setTransactionID(id: String){
        _transactionID.value = id
    }
}