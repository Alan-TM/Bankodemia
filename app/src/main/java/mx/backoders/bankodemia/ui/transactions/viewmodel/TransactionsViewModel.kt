package mx.backoders.bankodemia.ui.transactions.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.MakeTransactionDto
import mx.backoders.bankodemia.common.model.Transactions.MakeTransactionResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import mx.backoders.bankodemia.common.utils.PaymentType
import mx.backoders.bankodemia.common.utils.PaymentType.PAYMENT
import java.io.IOException

class TransactionsViewModel(stateHandle: SavedStateHandle) : ViewModel() {
    private val _transactionResponse = MutableLiveData<MakeTransactionResponse>()

    private val _errorResponse = MutableLiveData(0)
    val errorResponse: LiveData<Int> get() = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _contactID = stateHandle.getLiveData("contactID", "")
    val contactID: LiveData<String> get() = _contactID

    private val _contactFullName = stateHandle.getLiveData("contactFullName", "")
    val contactFullName: LiveData<String> get() = _contactFullName

    private val _paymentType = stateHandle.getLiveData("paymentType", PAYMENT)

    private val _transactionBody =
        stateHandle.getLiveData("transactionBody", MakeTransactionDto(0.0, "", "", ""))
    val transactionBody: LiveData<MakeTransactionDto> = _transactionBody

    private val serviceNetwork = ServiceNetwork()

    fun makeTransaction() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _transactionBody.value?.let { transaction ->
                    val response = serviceNetwork.makeTransactionPayment(transaction)

                    if (response.isSuccessful) {
                        _transactionResponse.value = response.body()
                        _isLoading.value = false
                    } else _errorResponse.value = response.code()
                }
            } catch (e: IOException) {
                //TODO add a code for the error response
            }
        }
    }

    fun setErrorCode(code: Int) {
        _errorResponse.value = code
    }

    fun makeTransactionBody(concept: String, amount: Double) {
        _transactionBody.value = if(_paymentType.value == PAYMENT) MakeTransactionDto(
            amount,
            concept,
            _contactID.value,
            _paymentType.value!!.type
        ) else
            MakeTransactionDto(
                amount,
                concept,
                null,
                _paymentType.value!!.type
            )
    }

    fun setContactInformation(userID: String, userFullName: String, paymentType: PaymentType) {
        _contactID.value = userID
        _contactFullName.value = userFullName
        _paymentType.value = paymentType
    }

    fun clearStateHandle(){
        _transactionBody.value = MakeTransactionDto(0.0, "", "", "")
    }

    fun validateTextField(text: String): Boolean = text.isNotEmpty()


}