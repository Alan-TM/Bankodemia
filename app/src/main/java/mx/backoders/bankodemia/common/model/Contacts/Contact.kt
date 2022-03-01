package mx.backoders.bankodemia.common.model.Contacts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mx.backoders.bankodemia.common.model.User.User

data class Contact(
    @Expose
    @SerializedName("shortName")
    val shortName: String,
    @Expose
    @SerializedName("owner")
    val owner: User,
    @Expose
    @SerializedName("user")
    val user: User
)
