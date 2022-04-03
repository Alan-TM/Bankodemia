package mx.backoders.bankodemia.common.api

import android.view.SurfaceControl
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.dto.MakeTransactionDto
import mx.backoders.bankodemia.common.dto.SaveContactDto
import mx.backoders.bankodemia.common.dto.UserSignUpDto
import mx.backoders.bankodemia.common.model.Contacts.ListMyContactsResponse
import mx.backoders.bankodemia.common.model.Contacts.SaveContactResponse
import mx.backoders.bankodemia.common.model.Transactions.MakeTransactionResponse
import mx.backoders.bankodemia.common.model.Transactions.Transaction
import mx.backoders.bankodemia.common.model.Transactions.TransactionDetailsResponse
import mx.backoders.bankodemia.common.model.User.*
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import retrofit2.Response
import retrofit2.http.*

//this should be deleted after implementing the auth interceptor
private const val TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjFmYTNmYjhjZTZjNDc4ZDBlMWI5OTEiLCJpYXQiOjE2NDczNzMwNTcsImV4cCI6MTY0NzQ1OTQ1N30.-o66ywoudbdYQF0i1VA37ibxmeA5Kiqf0TPjrOLs_w4"

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
    //@Headers("Authorization: Bearer $TOKEN")
    @GET("users/me/profile")
    suspend fun getUserFullProfile(): Response<UserFullProfileResponse>

    @GET("users")
    suspend fun getAllUsers(): Response<AllUsers>

    //@Headers("Authorization: Bearer $TOKEN")
    @GET("transactions/{id}")
    suspend fun getTransactionDetails(@Path("id") id: String): Response<TransactionDetailsResponse>

    @GET("contacts")
    suspend fun getContactList(): Response<ListMyContactsResponse>

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("contacts")
    suspend fun saveContact(@Body body: SaveContactDto): Response<SaveContactResponse>

    @POST("transactions")
    suspend fun makeTransactionPayment(@Body body: MakeTransactionDto): Response<MakeTransactionResponse>
}