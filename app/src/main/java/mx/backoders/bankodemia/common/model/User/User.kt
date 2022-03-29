package mx.backoders.bankodemia.common.model.User

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @Expose
    @SerializedName("email")
    var email: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("lastName")
    val lastName: String,
    @Expose
    @SerializedName("occupation")
    val occupation: String,
    @Expose
    @SerializedName("birthDate")
    val birthDate: String,
    @Expose
    @SerializedName("password")
    val password: String,
    @Expose
    @SerializedName("phone")
    val phone: String,
    @Expose
    @SerializedName("isPhoneVerified")
    val isPhoneVerified: Boolean,
    @Expose
    @SerializedName("identityImage")
    val identityImage: String,
    @Expose
    @SerializedName("identityImageType")
    val identityImageType: String,
    @Expose
    @SerializedName("_id")
    val id: String,
    @Expose
    @SerializedName("__v")
    val v: Int // ????? From API Response
)