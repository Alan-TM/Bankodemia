package mx.backoders.bankodemia.common.model.contacts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SaveContact(
    @Expose
    @SerializedName("_id")
    val id: String,
    @Expose
    @SerializedName("shortName")
    val shortName: String,
    @Expose
    @SerializedName("owner")
    val ownerID: String,
    @Expose
    @SerializedName("user")
    val userID: String
)
