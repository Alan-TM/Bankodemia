package mx.backoders.bankodemia.common.model.transactions

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListMyTransactionsData(
    @Expose
    @SerializedName("transactions")
    val transactions: ArrayList<Transaction>
)
