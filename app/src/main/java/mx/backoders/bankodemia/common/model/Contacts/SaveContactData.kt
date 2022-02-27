package mx.backoders.bankodemia.common.model.Contacts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SaveContactData(
    @Expose
    @SerializedName("contact")
    val contact: Contact
)
