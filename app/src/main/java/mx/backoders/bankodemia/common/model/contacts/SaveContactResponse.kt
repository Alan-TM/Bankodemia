package mx.backoders.bankodemia.common.model.contacts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SaveContactResponse(
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("data")
    val data: SaveContactResponseData
)
