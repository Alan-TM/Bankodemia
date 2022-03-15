package mx.backoders.bankodemia.ui.home.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
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
import java.util.*
import kotlin.collections.ArrayList

@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading //these are the values that we need to observe
    private val _userProfileResponse = MutableLiveData<UserFullProfileResponse>()
    val userProfileResponse: LiveData<UserFullProfileResponse> get() = _userProfileResponse
    private val _userProfileResponseError = MutableLiveData<String>() //should add an enum or sealed class, for better error management
    val userProfileResponseError: LiveData<String> get() = _userProfileResponseError

    //testing bottom navigation visibility when changing fragments
    private val _bottomNavIsvisible = MutableLiveData<Boolean>()
    val bottomNavIsVisible: LiveData<Boolean> get() = _bottomNavIsvisible

    private val serviceNetwork = ServiceNetwork()

    private val transactionItems = ArrayList<Transaction>()
    val transactionItemsForRecycler = ArrayList<TransactionListItem>()

    fun getUserProfile(){
        viewModelScope.launch {
            _isLoading.postValue(true)
            try{
                val response = serviceNetwork.getUserProfile()

                if(response.isSuccessful){
                    _userProfileResponse.postValue(response.body())

                    response.body()!!.data.transactions?.let { transactionItems.addAll(it) }
                    buildItemsForRecycler()

                    Log.e("PROFILE", response.body().toString())
                } else if(response.code() == 401){
                    Log.e("PROFILE", "required!!")
                }
            } catch(e: Exception){
                _userProfileResponseError.postValue(e.message)
            }
        }
    }

    private fun buildItemsForRecycler(){
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(Locale("es", "ES"))

        if (transactionItems.isNotEmpty()) {
            var date = ZonedDateTime.parse(transactionItems.first().createdAt)
            transactionItemsForRecycler.add(TransactionListItem.DateItem(date
                .format(formatter)
                .uppercase()
            ))

            transactionItems.forEach {
                val nextDate = ZonedDateTime.parse(it.createdAt)
                if (date.dayOfMonth != nextDate.dayOfMonth || date.month != nextDate.month || date.year != nextDate.year) {
                    transactionItemsForRecycler.add(TransactionListItem.DateItem(nextDate
                        .format(formatter)
                        .uppercase()
                    ))
                    date = nextDate
                }
                transactionItemsForRecycler.add(TransactionListItem.TransactionItem(it))
            }
        }
    }

    fun bottomNavIsVisible(visibility: Boolean){
        _bottomNavIsvisible.value = visibility
    }
}