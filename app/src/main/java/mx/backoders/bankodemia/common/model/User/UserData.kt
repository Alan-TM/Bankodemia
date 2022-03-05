package mx.backoders.bankodemia.common.model.User

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import mx.backoders.bankodemia.common.model.Transactions.Transaction

data class UserData(
    @Expose
    @SerializedName("user")
    var user: User,
    @Expose
    @SerializedName("transactions")
    val transactions: ArrayList<Transaction>? = null,
    @Expose
    @SerializedName("balance")
    val balance: Double? = null

    //Alan: Leave attributes with null safety ?-- Recycle this data class for UserSignUpResponse and UserFullProfileResponse
)
