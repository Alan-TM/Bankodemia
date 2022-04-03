package mx.backoders.bankodemia.ui.transactions.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.SaveContactDto
import mx.backoders.bankodemia.common.model.Contacts.SaveContactResponse
import mx.backoders.bankodemia.common.model.User.AllUsers
import mx.backoders.bankodemia.common.model.User.User
import mx.backoders.bankodemia.common.model.User.Users
import mx.backoders.bankodemia.common.service.ServiceNetwork
import java.io.IOException

class AddContactViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _allUsers = MutableLiveData<AllUsers>()
    val allUsers: LiveData<AllUsers> = _allUsers

    private val _listForView = MutableLiveData<ArrayList<String>>()
    val listForView: LiveData<ArrayList<String>> = _listForView

    private val _currentUser = MutableLiveData<User>()
    private val _shortName = MutableLiveData<String>()

    private lateinit var contactBody: SaveContactDto

    private val _saveContactResponse = MutableLiveData<SaveContactResponse>()
    val saveContactResponse: LiveData<SaveContactResponse> = _saveContactResponse

    private val serviceNetwork = ServiceNetwork()

    fun getAllUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = serviceNetwork.getAllUsers()

                if (response.isSuccessful) {
                    _allUsers.value = response.body()
                    buildItemsForView(response.body()!!.data.users)
                    _isLoading.value = false
                }
            } catch (e: IOException) {
                //TODO add io exception
            }
        }
    }

    private fun buildItemsForView(users: List<User>) {
        val dummyList = ArrayList<String>()

        for (user in users) {
            dummyList.add("${user.name} ${user.lastName}")
        }

        _listForView.value = dummyList
    }

    fun getUserFromList(position: Int) {
        _currentUser.value = _allUsers.value!!.data.users[position]
    }

    fun setShortNameForContact(shortName: String) {
        if (shortName.isNotEmpty() || shortName.isNotBlank()) {
            _shortName.value = shortName
        } else {
            _currentUser.value.let {
                _shortName.value = "${it?.name} ${it?.lastName}"
            }
        }
    }

    fun makeContactBody(){
        contactBody = SaveContactDto(_shortName.value!!, _currentUser.value!!.id)
    }

    fun sendContactDto(){
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = serviceNetwork.saveContact(contactBody)
                if(response.isSuccessful){
                    _saveContactResponse.value = response.body()
                    _isLoading.value = false
                }
            } catch(e: IOException){
                //TODO add exception
            }
        }
    }
}