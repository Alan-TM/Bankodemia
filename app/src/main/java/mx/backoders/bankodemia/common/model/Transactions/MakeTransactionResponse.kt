package mx.backoders.bankodemia.common.model.Transactions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mx.backoders.bankodemia.common.model.Transactions.Transaction

data class MakeTransactionResponse(
    @Expose
    @SerializedName("success")
    val success: Boolean,
    @Expose
    @SerializedName("data")
    val data: TransactionData
)
