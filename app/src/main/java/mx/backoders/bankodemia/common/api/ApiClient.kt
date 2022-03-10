package mx.backoders.bankodemia.common.api

import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.dto.UserSignUpDto
import mx.backoders.bankodemia.common.model.User.User
import mx.backoders.bankodemia.common.model.User.UserData
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.model.User.UserSignUpResponse
import mx.backoders.bankodemia.common.model.UserLoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {

    //send a body with email and password
    //and return a UserLoginResponse
    @POST("auth/login")
    suspend fun getLogin(
        @Query("expires_in") expires_in: String, //ENUM ?? THIS PARAMETER IS NOT NEEDED
        @Body loginDto: LoginDto //send the body.
    ): Response<UserLoginResponse>

    @POST("users")
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    suspend fun userSignUp(@Body body: UserSignUpDto): Response<UserSignUpResponse>

    //this should not be hardcoded
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjFmYTNmYjhjZTZjNDc4ZDBlMWI5OTEiLCJpYXQiOjE2NDY5NDQ5NzQsImV4cCI6MTY0NzAzMTM3NH0.SnD7qHgqaVapOfNK-yMGuibfpZEIcGcCyx6HKwNmaAI")
    @GET("users/me/profile")
    suspend fun getUserFullProfile(): Response<UserFullProfileResponse>
}