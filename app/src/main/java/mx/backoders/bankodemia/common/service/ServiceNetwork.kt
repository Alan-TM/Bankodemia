package mx.backoders.bankodemia.common.service

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.backoders.bankodemia.common.api.ApiClient
import mx.backoders.bankodemia.common.api.RetrofitInstance
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.dto.UserSignUpDto
import mx.backoders.bankodemia.common.model.Transactions.MakeTransactionResponse
import mx.backoders.bankodemia.common.model.Transactions.Transaction
import mx.backoders.bankodemia.common.model.Transactions.TransactionDetailsResponse
import mx.backoders.bankodemia.common.model.User.User
import mx.backoders.bankodemia.common.model.User.UserData
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.model.User.UserSignUpResponse
import mx.backoders.bankodemia.common.model.UserLoginResponse
import retrofit2.Response

class ServiceNetwork {
    private val retrofit = RetrofitInstance.getRetrofit().create(ApiClient::class.java)

    suspend fun getLogin(expires_in : String, dto: LoginDto) : Response<UserLoginResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.getLogin(expires_in, dto)
            response
        }
    }

    suspend fun getUserProfile() : Response<UserFullProfileResponse> = withContext(Dispatchers.IO){
        retrofit.getUserFullProfile()
    }

    suspend fun userSignUp(body: UserSignUpDto): Response<UserSignUpResponse> = withContext(Dispatchers.IO){
        retrofit.userSignUp(body)
    }

    suspend fun getTransactionDetails(id: String): Response<TransactionDetailsResponse> = withContext(Dispatchers.IO){
        retrofit.getTransactionDetails(id)
    }
}