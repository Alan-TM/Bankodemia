package mx.backoders.bankodemia.common.api

import mx.backoders.bankodemia.common.preferences.SharedPreferencesInstance
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val API_URL = "https://bankodemia.kodemia.mx/"

object RetrofitInstance {
    fun getRetrofit(): Retrofit {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .addInterceptor(AuthInterceptor())
            .build()
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //TODO add an interceptor for

    class AuthInterceptor : Interceptor{
        override fun intercept(chain: Interceptor.Chain): Response {
            val sharedPreferences = SharedPreferencesInstance
            val requestBuilder = chain.request().newBuilder()

            sharedPreferences.getPreference("token")?.let{
                requestBuilder.addHeader("Authorization", "Bearer $it")
            }

            return chain.proceed(requestBuilder.build())
        }
    }
}