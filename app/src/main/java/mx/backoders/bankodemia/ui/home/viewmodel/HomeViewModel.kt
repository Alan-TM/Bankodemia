package mx.backoders.bankodemia.ui.home.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.adapters.TransactionListItem
import mx.backoders.bankodemia.common.model.Transactions.Transaction
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val userProfileResponse = MutableLiveData<UserFullProfileResponse>()
    val error = MutableLiveData<String>()
    private val serviceNetwork = ServiceNetwork()

    private val transactionItems = ArrayList<Transaction>()
    val transactionItemsForRecycler = ArrayList<TransactionListItem>()

    fun getUserProfile(){
        viewModelScope.launch {
            isLoading.postValue(true)
            try{
                val response = serviceNetwork.getUserProfile()

                if(response.isSuccessful){
                    userProfileResponse.postValue(response.body())

                    response.body()!!.data.transactions?.let { transactionItems.addAll(it) }
                    buildItemsForRecycler()

                    Log.e("PROFILE", response.body().toString())
                } else if(response.code() == 401){
                    Log.e("PROFILE", "required!!")
                }
            } catch(e: Exception){
                error.postValue(e.message)
            }
        }
    }

    private fun buildItemsForRecycler(){
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        if (transactionItems.isNotEmpty()) {
            var date = ZonedDateTime.parse(transactionItems.first().createdAt)
            transactionItemsForRecycler.add(TransactionListItem.DateItem(date.format(formatter)))

            transactionItems.forEach {
                val nextDate = ZonedDateTime.parse(it.createdAt)
                if (date.dayOfMonth != nextDate.dayOfMonth || date.month != nextDate.month || date.year != nextDate.year) {
                    transactionItemsForRecycler.add(TransactionListItem.DateItem(nextDate.format(formatter)))
                    date = nextDate
                }
                transactionItemsForRecycler.add(TransactionListItem.TransactionItem(it))
            }
        }

        transactionItemsForRecycler.forEach {
            when(it){
                is TransactionListItem.DateItem -> Log.e("Date", it.date)
                is TransactionListItem.TransactionItem -> Log.e("Transaction", it.transaction.concept)
            }
        }
    }
}