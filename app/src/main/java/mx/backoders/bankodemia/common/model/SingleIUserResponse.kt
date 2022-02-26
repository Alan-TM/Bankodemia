package mx.backoders.bankodemia.common.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mx.backoders.bankodemia.common.dto.User

data class SingleIUserResponse(
    @Expose
    @SerializedName("success")
    var success: Int,
    @Expose
    @SerializedName("data")
    val data: User
)
