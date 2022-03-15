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
    private val _saveContactResponse = MutableLiveData<SaveContactResponse>()
    val contactListResponse: LiveData<SaveContactResponse> get() = _saveContactResponse

    private val serviceNetwork = ServiceNetwork()

    fun saveContact(){
        //Delete this after
        val contact = SaveContactDto("El mero", "622007768ce6c478d0e1b997")

        /*
        REMINDER: API doesn't accept regular strings IDs, need to check this with our mentor
        This doesn't work atm.
         */

        viewModelScope.launch {
            try{
                val response = serviceNetwork.saveContact(contact)

                if(response.isSuccessful){
                    _saveContactResponse.value = response.body()
                    Log.e("CONTACTS", response.body().toString())
                } else{
                    Log.e("CONTACTS_ERROR", response.errorBody().toString())
                }
            }catch(e: IOException){
                Log.e("CONTACTS_ERROR", e.localizedMessage)
            }
        }
    }
}