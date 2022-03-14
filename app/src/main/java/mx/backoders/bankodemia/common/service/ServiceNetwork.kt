package mx.backoders.bankodemia.common.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.backoders.bankodemia.common.api.ApiClient
import mx.backoders.bankodemia.common.api.RetrofitInstance
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.dto.UserSignUpDto
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.model.User.UserSignUpResponse
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import retrofit2.Response

class ServiceNetwork {
    private val retrofit = RetrofitInstance.getRetrofit().create(ApiClient::class.java)

    suspend fun login(login: LoginDto):Response<UserLoginResponse>{
        return withContext(Dispatchers.IO){
            val response = retrofit.login(login)
            response
        }
    }

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
}