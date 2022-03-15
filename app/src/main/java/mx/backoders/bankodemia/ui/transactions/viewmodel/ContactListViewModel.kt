package mx.backoders.bankodemia.ui.transactions.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.backoders.bankodemia.common.dto.SaveContactDto
import mx.backoders.bankodemia.common.model.Contacts.ListMyContactsResponse

class ContactListViewModel : ViewModel() {
    private val _contactListResponse = MutableLiveData<ListMyContactsResponse>()
    val contactListResponse: LiveData<ListMyContactsResponse> get() = _contactListResponse

    fun saveContact(){
        //Delete this after
        val contact = SaveContactDto("El mero", "622007768ce6c478d0e1b997")
    }
}