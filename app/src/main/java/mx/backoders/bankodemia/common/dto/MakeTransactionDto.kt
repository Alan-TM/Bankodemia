package mx.backoders.bankodemia.common.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MakeTransactionDto(
    @Expose
    @SerializedName("amount")
    val amount: Double,
    @Expose
    @SerializedName("concept")
    val concept: String,
    @Expose
    @SerializedName("destinationUser")
    val destinationUser: String?, //required when type is PAYMENT
    @Expose
    @SerializedName("type")
    val type: String //ENUM ?
)
