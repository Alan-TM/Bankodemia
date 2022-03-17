package mx.backoders.bankodemia.ui.transactions.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.service.ServiceNetwork

class TransactionsViewModel : ViewModel() {


    private val serviceNetwork = ServiceNetwork()

    fun makeTransaction(){

    }
}