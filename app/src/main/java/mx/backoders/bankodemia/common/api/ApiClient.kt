package mx.backoders.bankodemia.common.api

import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.model.User.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {

    @GET("users")
    suspend fun getLogin(@Query("id") id: Int) : Response<UserData>

    @POST("endpoint")
    suspend fun userLogIn(@Body body: LoginDto){
    }
}