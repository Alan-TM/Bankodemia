package mx.backoders.bankodemia.common.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserLoginResponse(
    @Expose
    @SerializedName("token")
    var token: String?,
    @Expose
    @SerializedName("expiresIn")
    val expiresIn: String?
)
