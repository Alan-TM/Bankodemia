package mx.backoders.bankodemia.common.model.contacts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SaveContactResponseData(
    @Expose
    @SerializedName("contact")
    val contact: SaveContact
)
