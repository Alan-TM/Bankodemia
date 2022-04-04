package mx.backoders.bankodemia.common.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.backoders.bankodemia.common.api.ApiClient
import mx.backoders.bankodemia.common.api.RetrofitInstance
import mx.backoders.bankodemia.common.dto.LoginDto
import mx.backoders.bankodemia.common.dto.MakeTransactionDto
import mx.backoders.bankodemia.common.dto.SaveContactDto
import mx.backoders.bankodemia.common.dto.UserSignUpDto
import mx.backoders.bankodemia.common.model.contacts.ListMyContactsResponse
import mx.backoders.bankodemia.common.model.contacts.SaveContactResponse
import mx.backoders.bankodemia.common.model.transactions.MakeTransactionResponse
import mx.backoders.bankodemia.common.model.transactions.TransactionDetailsResponse
import mx.backoders.bankodemia.common.model.user.AllUsers
import mx.backoders.bankodemia.common.model.user.UserFullProfileResponse
import mx.backoders.bankodemia.common.model.user.UserSignUpResponse
import mx.backoders.bankodemia.common.model.login.UserLoginResponse
import retrofit2.Response

class ServiceNetwork {
    private val retrofit = RetrofitInstance.getRetrofit().create(ApiClient::class.java)

    suspend fun login(login: LoginDto): Response<UserLoginResponse> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.login(login)
            response
        }
    }

    suspend fun getLogin(expires_in: String, dto: LoginDto): Response<UserLoginResponse> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getLogin(expires_in, dto)
            response
        }
    }

    suspend fun getUserProfile(): Response<UserFullProfileResponse> = withContext(Dispatchers.IO) {
        retrofit.getUserFullProfile()
    }

    suspend fun userSignUp(body: UserSignUpDto): Response<UserSignUpResponse> =
        withContext(Dispatchers.IO) {
            retrofit.userSignUp(body)
        }

    suspend fun getTransactionDetails(id: String): Response<TransactionDetailsResponse> =
        withContext(Dispatchers.IO) {
            retrofit.getTransactionDetails(id)
        }

    suspend fun getContactList(): Response<ListMyContactsResponse> = withContext(Dispatchers.IO) {
        retrofit.getContactList()
    }

    suspend fun saveContact(body: SaveContactDto): Response<SaveContactResponse> =
        withContext(Dispatchers.IO) {
            retrofit.saveContact(body)
        }

    suspend fun getAllUsers(): Response<AllUsers> =
        withContext(Dispatchers.IO){
            retrofit.getAllUsers()
        }


    suspend fun makeTransactionPayment(body: MakeTransactionDto): Response<MakeTransactionResponse> =
        withContext(Dispatchers.IO) {
            retrofit.makeTransactionPayment(body)
        }
}