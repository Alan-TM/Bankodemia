package mx.backoders.bankodemia.common.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @Expose
    @SerializedName("user")
    var user: User,
    @Expose
    @SerializedName("transactions")
    val transactions: ArrayList<Transactions>,
    @Expose
    @SerializedName("balance")
    val balance: String
)
