package mx.backoders.bankodemia.common.service

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.backoders.bankodemia.common.api.ApiClient
import mx.backoders.bankodemia.common.api.RetrofitInstance
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.model.User.User
import mx.backoders.bankodemia.common.model.User.UserData
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.model.UserLoginResponse
import retrofit2.Response

class ServiceNetwork {
    private val retrofit = RetrofitInstance.getRetrofit().create(ApiClient::class.java)

    suspend fun getLogin(expires_in : Int, dto: LoginDto) : Response<UserLoginResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.getLogin(expires_in, dto)
            response
        }
    }

    suspend fun getUserProfile() : Response<UserFullProfileResponse> = withContext(Dispatchers.IO){
        Log.e("PROFILE", "si entra en el service")
        retrofit.getUserFullProfile()
    }
}