package mx.backoders.bankodemia.common.model.Transactions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mx.backoders.bankodemia.common.model.User.User

data class Transaction(
    @Expose
    @SerializedName("amount")
    var amount: Double,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("concept")
    val concept: String,
    @Expose
    @SerializedName("created_at")
    val createdAt: String,
    @Expose
    @SerializedName("issuer")
    val issuer: User,
    @Expose
    @SerializedName("destinationUser")
    val destinationUser: User,
    @Expose
    @SerializedName("isIncome")
    val isIncome: Boolean,
    @Expose
    @SerializedName("_id")
    val _id: String

)
