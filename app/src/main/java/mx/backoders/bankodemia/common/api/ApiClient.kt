package mx.backoders.bankodemia.common.api

import mx.backoders.bankodemia.common.dto.Data
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("users")
    suspend fun getLogin(@Query("id") id: Int) : Response<Data>


}