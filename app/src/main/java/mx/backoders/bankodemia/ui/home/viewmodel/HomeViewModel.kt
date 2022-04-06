package mx.backoders.bankodemia.ui.home.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.adapters.TransactionListItem
import mx.backoders.bankodemia.common.model.transactions.Transaction
import mx.backoders.bankodemia.common.model.user.UserFullProfileResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel() : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading
    private val _userProfileResponse = MutableLiveData<UserFullProfileResponse>()
    val userProfileResponse: LiveData<UserFullProfileResponse> get() = _userProfileResponse
    private val _userProfileResponseError =
        MutableLiveData<String>()
    val userProfileResponseError: LiveData<String> get() = _userProfileResponseError

    private val _bottomNavIsVisible = MutableLiveData<Boolean>()
    val bottomNavIsVisible: LiveData<Boolean> get() = _bottomNavIsVisible

    private val _topToolbarIsVisible = MutableLiveData<Boolean>()
    val topToolbarIsVisible: LiveData<Boolean> get() = _topToolbarIsVisible

    private val _onBackPressedEnable = MutableLiveData<Boolean>()
    val onBackPressedEnable: LiveData<Boolean> = _onBackPressedEnable

    private val serviceNetwork = ServiceNetwork()

    private var transactionItems = ArrayList<Transaction>()
    val transactionItemsForRecycler = ArrayList<TransactionListItem>()

    fun getUserProfile() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val response = serviceNetwork.getUserProfile()

                if (response.isSuccessful) {
                    _userProfileResponse.postValue(response.body())

                    response.body()!!.data.transactions?.let { transactionItems = it }
                    buildItemsForRecycler()

                    Log.e("PROFILE", response.body().toString())
                } else if (response.code() == 401) {
                    Log.e("PROFILE", "required!!")
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _userProfileResponseError.postValue(e.message)
            }
        }
    }

    private fun buildItemsForRecycler() {
        transactionItemsForRecycler.clear()
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            .withLocale(
                Locale("es", "ES")
            )

        if (transactionItems.isNotEmpty()) {
            var date = ZonedDateTime.parse(transactionItems.first().createdAt)
            transactionItemsForRecycler.add(
                TransactionListItem.DateItem(
                    date
                        .format(formatter)
                        .uppercase()
                )
            )

            transactionItems.forEach {
                val nextDate = ZonedDateTime.parse(it.createdAt)
                if (date.dayOfMonth != nextDate.dayOfMonth || date.month != nextDate.month || date.year != nextDate.year) {
                    transactionItemsForRecycler.add(
                        TransactionListItem.DateItem(
                            nextDate
                                .format(formatter)
                                .uppercase()
                        )
                    )
                    date = nextDate
                }
                transactionItemsForRecycler.add(TransactionListItem.TransactionItem(it))
            }
        }
    }

    fun setOnBackPressedEnable(isEnable: Boolean) {
        _onBackPressedEnable.value = isEnable
    }

    fun bottomNavIsVisible(visibility: Boolean) {
        _bottomNavIsVisible.value = visibility
    }

    fun topToolbarIsVisible(visibility: Boolean) {
        _topToolbarIsVisible.value = visibility
    }
}