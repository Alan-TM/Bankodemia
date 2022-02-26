package mx.backoders.bankodemia.common.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Transactions(
    @Expose
    @SerializedName("amount")
    var amount: String,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("created_at")
    val created_at: String,
    @Expose
    @SerializedName("issuer")
    val issuer: User,
    @Expose
    @SerializedName("destinationUser")
    val destinationUser: User,
    @Expose
    @SerializedName("isIncome")
    val isIncome: Boolean
)
