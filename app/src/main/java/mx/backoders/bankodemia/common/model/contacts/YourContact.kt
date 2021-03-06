package mx.backoders.bankodemia.common.model.contacts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mx.backoders.bankodemia.common.model.user.User

data class YourContact(
    @Expose
    @SerializedName("_id")
    val id: String,
    @Expose
    @SerializedName("user")
    val user: User,
    @Expose
    @SerializedName("owner")
    val owner: User,
    @Expose
    @SerializedName("shortName")
    val shortName: String
)
