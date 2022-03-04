package mx.backoders.bankodemia.common.api

import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.model.User.User
import mx.backoders.bankodemia.common.model.User.UserData
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.model.UserLoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {

    //send a body with email and password
    //and return a UserLoginResponse
    @POST("auth/login")
    suspend fun getLogin(
        @Query("expires_in") expires_in: Int, //ENUM ?? THIS PARAMETER IS NOT NEEDED
        @Body loginDto: LoginDto //send the body.
    ): Response<UserLoginResponse>

    @POST("endpoint")
    suspend fun userLogIn(@Body body: LoginDto)

    //this should not be hardcoded
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjFmYTNmYjhjZTZjNDc4ZDBlMWI5OTEiLCJpYXQiOjE2NDY0MjE5MTksImV4cCI6MTY0NjQyNTUxOX0.zVQ9RrQsq849kEPhhaCwSndyeKixQut-Sgiw59rP0oE")
    @GET("users/me/profile")
    suspend fun getUserFullProfile(): Response<UserFullProfileResponse>
}