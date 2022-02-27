package mx.backoders.bankodemia.common.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.backoders.bankodemia.common.api.ApiClient
import mx.backoders.bankodemia.common.api.RetrofitInstance
import mx.backoders.bankodemia.common.model.User.UserData
import retrofit2.Response

class ServiceNetwork {
    private val retrofit = RetrofitInstance.getRetrofit().create(ApiClient::class.java)

    suspend fun getLogin(dato : Int) : Response<UserData> {
        return withContext(Dispatchers.IO){
            val respuesta = retrofit.getLogin(dato)
            respuesta
        }
    }
}