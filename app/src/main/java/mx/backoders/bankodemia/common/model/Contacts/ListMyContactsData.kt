package mx.backoders.bankodemia.common.model.Contacts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListMyContactsData(
    @Expose
    @SerializedName("contacts")
    val contacts: ArrayList<YourContact>
)