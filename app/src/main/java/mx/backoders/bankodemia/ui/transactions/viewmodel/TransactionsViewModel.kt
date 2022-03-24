package mx.backoders.bankodemia.ui.transactions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.MakeTransactionDto
import mx.backoders.bankodemia.common.model.Transactions.MakeTransactionResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import mx.backoders.bankodemia.common.utils.PaymentType.PAYMENT
import java.io.IOException

class TransactionsViewModel : ViewModel() {

    private val _transactionResponse = MutableLiveData<MakeTransactionResponse>()
    val transactionResponse: LiveData<MakeTransactionResponse> get() = _transactionResponse

    private val _errorResponse = MutableLiveData<Int>()
    val errorResponse: LiveData<Int> get() = _errorResponse


    private val transactionBody = MutableLiveData<MakeTransactionDto>()

    private val serviceNetwork = ServiceNetwork()

    fun makeTransaction() {
        viewModelScope.launch {
            try {
                transactionBody.value?.let { transaction ->
                    val response = serviceNetwork.makeTransactionPayment(transaction)

                    if (response.isSuccessful) _transactionResponse.value = response.body()
                    else _errorResponse.value = response.code()
                }
            } catch (e: IOException) {
                //TODO add a code for the error response
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