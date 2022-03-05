package mx.backoders.bankodemia.common.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginDto (
    @Expose
    @SerializedName("email")
    var email: String,
    @Expose
    @SerializedName("password")
    val password: String
)