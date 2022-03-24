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
import mx.backoders.bankodemia.common.utils.PaymentType.*
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class TransactionsViewModel : ViewModel() {

    private val _transactionResponse = MutableLiveData<MakeTransactionResponse>()
    val transactionResponse: LiveData<MakeTransactionResponse> get() = _transactionResponse

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String> get() = _errorResponse

    private val transactionBody = MutableLiveData<MakeTransactionDto>()

    private val serviceNetwork = ServiceNetwork()

    fun makeTransaction() {
        viewModelScope.launch {
            try {
                transactionBody.value?.let { transaction ->
                    val response = serviceNetwork.makeTransactionPayment(transaction)
                    if (response.isSuccessful) {
                        _transactionResponse.value = response.body()
                        Log.d("TRANSACTION", response.isSuccessful.toString())
                    }
                    else {
                        _errorResponse.value = when(response.code()){
                            401 -> "No autorizado"
                            402, 412 -> "No cuentas con el balance suficiente"
                            else -> "A ocurrido un error, verifica tus datos e intentalo de nuevo"
                        }
                    }
                }
            } catch (e: IOException) {
                _errorResponse.value =
                    "Revisa la información e intentelo de nuevo"
            }
        }
    }

    fun makeTransactionBody(destinationUserID: String, concept: String, amount: Double) {
        transactionBody.value = MakeTransactionDto(
            amount,
            concept,
            destinationUserID,
            PAYMENT.type
        )
    }

    fun validateTextField(text: String): Boolean = text.isNotEmpty()

}