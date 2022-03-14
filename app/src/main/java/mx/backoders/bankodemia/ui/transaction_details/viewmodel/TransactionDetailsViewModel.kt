package mx.backoders.bankodemia.ui.transaction_details.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.model.Transactions.MakeTransactionResponse
import mx.backoders.bankodemia.common.model.Transactions.Transaction
import mx.backoders.bankodemia.common.model.Transactions.TransactionDetailsResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import java.io.IOException

class TransactionDetailsViewModel : ViewModel() {

    private val serviceNetwork = ServiceNetwork()

    private val _transactionDetailsResponse = MutableLiveData<TransactionDetailsResponse>()
    val transactionDetailsResponse: LiveData<TransactionDetailsResponse> get() = _transactionDetailsResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _transactionDetailsError = MutableLiveData<String>()
    val transactionDetailsError: LiveData<String> get() = _transactionDetailsError

    fun fetchTransactionData(id: String){
        viewModelScope.launch {
            _isLoading.value = true
            try{
                val response = serviceNetwork.getTransactionDetails(id)

                if(response.isSuccessful){
                    _transactionDetailsResponse.value = response.body()
                    Log.e("TransactionDetails", response.body().toString())
                    _isLoading.value = false
                } else if(response.code() == 401){
                    Log.e("fetchTransactionDetails", "auth required!")
                } else if(response.code() == 404){
                    Log.e("fetchTransactionDetails", "id not found")
                }
                else{
                    _transactionDetailsError.value = response.errorBody().toString()
                }
            }catch(e: IOException){
                _transactionDetailsError.value = e.localizedMessage
            }
        }
    }
}