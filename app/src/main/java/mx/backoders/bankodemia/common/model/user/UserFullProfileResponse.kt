package mx.backoders.bankodemia.common.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserFullProfileResponse (
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("data")
    val data: UserData
    //Same as UserSignUpResponse??
)