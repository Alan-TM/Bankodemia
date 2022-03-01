package mx.backoders.bankodemia.common.model.Contacts

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListMyContactsResponse(
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("data")
    val data: ListMyContactsData
)