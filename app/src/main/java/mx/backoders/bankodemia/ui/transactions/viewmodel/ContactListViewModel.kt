package mx.backoders.bankodemia.ui.transactions.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.SaveContactDto
import mx.backoders.bankodemia.common.model.Contacts.ListMyContactsResponse
import mx.backoders.bankodemia.common.model.Contacts.SaveContactResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import java.io.IOException

class ContactListViewModel : ViewModel() {
    private val _contactListResponse = MutableLiveData<ListMyContactsResponse>()
    val contactListResponse: LiveData<ListMyContactsResponse> get() = _contactListResponse

    private val serviceNetwork = ServiceNetwork()


    fun getContactList(){
        viewModelScope.launch {
            try{
                val response = serviceNetwork.getContactList()

                if(response.isSuccessful){
                    _contactListResponse.value = response.body()
                } else{
                    Log.e("CONTACT_LIST", response.errorBody().toString())
                }
            } catch(e: IOException){
                Log.e("CONTACT_LIST", e.localizedMessage)
            }
        }
    }

    //add this to save contact fragment
    /*
    fun saveContact(){
        //Delete this after
        viewModelScope.launch {
            try{
                val response = serviceNetwork.saveContact(contact)

                if(response.isSuccessful){
                    _contactListResponse.value = response.body()
                    Log.e("CONTACTS", response.body().toString())
                } else{
                    Log.e("CONTACTS_ERROR", response.errorBody().toString())
                }
            }catch(e: IOException){
                Log.e("CONTACTS_ERROR", e.localizedMessage)
            }
        }
    }
     */
}