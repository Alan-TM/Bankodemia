package mx.backoders.bankodemia.common.model.transactions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListMyTransactionsResponse(
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("data")
    val data: ListMyTransactionsData
)
