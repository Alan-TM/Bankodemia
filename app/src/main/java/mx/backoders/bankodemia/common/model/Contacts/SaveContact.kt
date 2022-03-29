package mx.backoders.bankodemia.common.model.Contacts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mx.backoders.bankodemia.common.model.User.User

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
