package mx.backoders.bankodemia.ui.transactions.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.MakeTransactionDto
import mx.backoders.bankodemia.common.model.Transactions.MakeTransactionResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import mx.backoders.bankodemia.common.utils.PaymentType
import java.io.IOException

class TransactionsViewModel : ViewModel() {

    private val _transactionResponse = MutableLiveData<MakeTransactionResponse>()
    val transactionResponse: LiveData<MakeTransactionResponse> get() = _transactionResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse

    private val transactionBody = MutableLiveData<MakeTransactionDto>()

    private val serviceNetwork = ServiceNetwork()

    fun makeTransaction(){
        viewModelScope.launch {
            try{
                transactionBody.value?.let { transaction ->
                    val response = serviceNetwork.makeTransactionPayment(transaction)

                    if(response.isSuccessful){
                        _transactionResponse.value = response.body()
                    } else if(response.code() == 412){
                        //should not be hardcoded
                        _errorResponse.value = "No cuentas con el balance suficiente para realizar esta transacción"
                    }
                    else{
                        Log.e("MAKE_TRANSACTION_PAYMENT", response.errorBody().toString())
                    }
                } ?: Log.e("MAKE_TRANSACTION_PAYMENT", "no tiene nada")
            } catch(e: IOException){
                Log.e("MAKE_TRANSACTION_PAYMENT", e.localizedMessage!!)
            }
        }
    }

    fun makeTransactionBody(destinationUserID: String, concept: String, amount: Double){
        transactionBody.value = MakeTransactionDto(amount,
            concept,
            destinationUserID,
            PaymentType.PAYMENT.type
        )
    }
}