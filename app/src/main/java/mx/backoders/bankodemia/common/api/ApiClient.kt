package mx.backoders.bankodemia.common.api

import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.model.User.User
import mx.backoders.bankodemia.common.model.User.UserData
import mx.backoders.bankodemia.common.model.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {

    //this needs an expires_in parameter
    //send a body with email and password
    //and return a UserLoginResponse
    @POST("auth/login")
    suspend fun getLogin(
        @Query("expires_in") expires_in: Int, //ENUM ??
        @Body loginDto: LoginDto //send the body.
    ): Response<UserLoginResponse>

    @POST("endpoint")
    suspend fun userLogIn(@Body body: LoginDto) {
    }
}