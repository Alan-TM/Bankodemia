package mx.backoders.bankodemia.common.model.Transactions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransactionData(
    val transaction: Transaction,
    @Expose
    @SerializedName("finalBalance")
    val finalBalance: Double
)
