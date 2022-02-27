package mx.backoders.bankodemia.common.model.User

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserSignUpResponse(
    @Expose
    @SerializedName("success")
    var success: Boolean,
    @Expose
    @SerializedName("data")
    val data: UserData //recycle UserData
    //Alan: Use this data class for GET a user API call?
    //Also, same data as UserFullProfileResponse
)
