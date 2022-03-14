package mx.backoders.bankodemia.common.api

import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.dto.UserSignUpDto
import mx.backoders.bankodemia.common.model.User.User
import mx.backoders.bankodemia.common.model.User.UserData
import mx.backoders.bankodemia.common.model.User.UserFullProfileResponse
import mx.backoders.bankodemia.common.model.User.UserSignUpResponse
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiClient {
    @POST("auth/login")
    suspend fun login(
        @Body loginDto: LoginDto
    ):Response<UserLoginResponse>

    //send a body with email and password
    //and return a UserLoginResponse
    @POST("auth/login")
    suspend fun getLogin(
        @Query("expires_in") expires_in: String, //TODO Roberto to Alan, Is needed? THIS PARAMETER IS NOT NEEDED
        @Body loginDto: LoginDto //send the body.
    ): Response<UserLoginResponse>

    @POST("users")
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    suspend fun userSignUp(@Body body: UserSignUpDto): Response<UserSignUpResponse>

    //this should not be hardcoded
    @Headers("Authorization: Bearer " +
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjIwMDc3NjhjZTZjNDc4ZDBlMWI5OTciLCJpYXQiOjE2NDcyMjM4OTgsImV4cCI6MTY0NzIyNzQ5OH0.6ngud4kOJD_P8iGYRkvY8WwKFKTY12UI1f1__9epaQ4")
    @GET("users/me/profile")
    suspend fun getUserFullProfile(): Response<UserFullProfileResponse>
}