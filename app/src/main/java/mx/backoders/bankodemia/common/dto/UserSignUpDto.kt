package mx.backoders.bankodemia.common.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//TODO RoHe: This is the same as USER???
data class UserSignUpDto(
    @Expose
    @SerializedName("birthDate")
    val birthDate: String,
    @Expose
    @SerializedName("email")
    var email: String,
    @Expose
    @SerializedName("identityImage")
    val identityImage: String,
    @Expose
    @SerializedName("identityImageType")
    val identityImageType: String, // TODO RoHe: This should be a ENUM?
    @Expose
    @SerializedName("lastName")
    val lastName: String,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("password")
    val password: String,
    @Expose
    @SerializedName("phone")
    val phone: String,
    @Expose
    @SerializedName("occupation")
    val occupation: String?
)
