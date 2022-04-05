package mx.backoders.bankodemia.ui.singup.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import mx.backoders.bankodemia.common.dto.UserSignUpDto
import mx.backoders.bankodemia.common.model.user.UserSignUpResponse
import mx.backoders.bankodemia.common.service.ServiceNetwork
import mx.backoders.bankodemia.common.utils.IdentityImageType
import mx.backoders.bankodemia.common.utils.IdentityImageType.*
import mx.backoders.bankodemia.common.utils.parseBirthdayForAPI
import mx.backoders.bankodemia.common.utils.parseBirthdayForView
import mx.backoders.bankodemia.common.utils.timeStampForImage
import java.io.File
import java.io.IOException
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class SignUpViewModel(stateHandle: SavedStateHandle) : ViewModel() {
    private val _email = stateHandle.getLiveData("email", "")
    val email: LiveData<String> = _email

    private val _phone = stateHandle.getLiveData("phone", "")
    val phone: LiveData<String> = _phone

    private val _phoneLada = stateHandle.getLiveData("phoneLada", "+52")
    val phoneLada: LiveData<String> = _phoneLada

    private val _name = stateHandle.getLiveData("name", "")
    val name: LiveData<String> = _name

    private val _lastName = stateHandle.getLiveData("lastName", "")
    val lastName: LiveData<String> = _lastName

    private val _occupation = stateHandle.getLiveData("occupation", "")
    val occupation: LiveData<String> = _occupation

    private val _birthdayForView = stateHandle.getLiveData("birthdayForView", "")
    val birthdayForView: LiveData<String> = _birthdayForView

    private val _birthdayForAPI = stateHandle.getLiveData("birthdayForAPI", "")

    private val _identityImageType = stateHandle.getLiveData("identityImageType", INE)
    private val _identityImageIne = stateHandle.getLiveData("INE", "")
    val identityImageIne: LiveData<String> = _identityImageIne
    private val _identityImageMigrationForm = stateHandle.getLiveData("MIGRATION_FORM", "")
    val identityImageMigrationForm: LiveData<String> = _identityImageMigrationForm
    private val _identityImagePassport = stateHandle.getLiveData("PASSPORT", "")
    val identityImagePassport: LiveData<String> = _identityImagePassport

    private val _password = stateHandle.getLiveData("password", "")
    val password: LiveData<String> = _password

    private val _userSignupDto = MutableLiveData<UserSignUpDto>()
    private val _userSignupResponse = MutableLiveData<UserSignUpResponse>()
    val userSignUpResponse: LiveData<UserSignUpResponse> = _userSignupResponse

    private val _errorResponse = MutableLiveData<Int>()
    val errorResponse: LiveData<Int> = _errorResponse

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val serviceNetwork = ServiceNetwork()

    fun decodeImageForAPI(image: File): String =
        Base64.getEncoder().encodeToString(image.absolutePath.toByteArray())

    fun createTimeStampForImage(): String = timeStampForImage()

    fun setIdentityImageType(identityType: IdentityImageType) {
        _identityImageType.value = identityType
    }

    fun setUserEmail(email: String) {
        _email.value = email
        Log.e("EMAIL", _email.value!!)
    }

    fun setUserPhone(phone: String) {
        _phone.value = phone
    }

    fun setUserPhoneLada(lada: String) {
        _phoneLada.value = lada
    }

    fun setUserPassword(password: String) {
        _password.value = password
    }

    fun setIdentityImage(image: String) {
        when (_identityImageType.value) {
            INE -> _identityImageIne.value = image
            PASSPORT -> _identityImagePassport.value = image
            else -> _identityImageMigrationForm.value = image
        }
    }

    fun setUserPersonalInfo(name: String, lastName: String, occupation: String) {
        if (name.isNotBlank())
            _name.value = name
        if (lastName.isNotBlank())
            _lastName.value = lastName
        if (occupation.isNotBlank())
            _occupation.value = occupation
    }

    fun setBirthdayParsers(day: Int, month: Int, year: Int) {
        _birthdayForView.value = parseBirthdayForView(day, month, year)
        _birthdayForAPI.value = parseBirthdayForAPI(day, month, year)
    }

    fun createUserSignUpDto() {
        val identityTypeAPI = identityImageSelectorByType()
        val fullPhone = "${_phoneLada.value}${_phone.value}"

        _userSignupDto.value = UserSignUpDto(
            _birthdayForAPI.value!!,
            _email.value!!,
            identityTypeAPI,
            _identityImageType.value!!.type,
            _lastName.value!!,
            _name.value!!,
            _password.value!!,
            fullPhone,
            _occupation.value!!
        )
    }

    private fun identityImageSelectorByType(): String {
        return when (_identityImageType.value) {
            INE -> _identityImageIne.value!!
            PASSPORT -> _identityImagePassport.value!!
            else -> _identityImageMigrationForm.value!!
        }
    }

    fun makeSignUpCall() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                Log.e("SIGNUP_DTO", _userSignupDto.value!!.name)
                Log.e("SIGNUP_DTO", _userSignupDto.value!!.lastName)
                Log.e("SIGNUP_DTO", _userSignupDto.value!!.email)
                Log.e("SIGNUP_DTO", _userSignupDto.value!!.occupation!!)
                Log.e("SIGNUP_DTO", _userSignupDto.value!!.birthDate)
                Log.e("SIGNUP_DTO", _userSignupDto.value!!.phone)
                Log.e("SIGNUP_DTO", _userSignupDto.value!!.password)
                Log.e("SIGNUP_DTO", _userSignupDto.value!!.identityImageType)
                Log.e("SIGNUP_DTO", _userSignupDto.value!!.identityImage)
                val response = serviceNetwork.userSignUp(_userSignupDto.value!!)

                if (response.isSuccessful) {
                    _userSignupResponse.value = response.body()
                    _isLoading.value = false
                } else {
                    _errorResponse.value = response.code()
                }
            } catch (e: IOException) {
                _errorResponse.value = 900
            }
        }
    }

    fun justForTest(){
        Log.e("SIGNUP", _email.value!!)
        Log.e("SIGNUP", _name.value!!)
        Log.e("SIGNUP", _lastName.value!!)
        Log.e("SIGNUP", _occupation.value!!)
        Log.e("SIGNUP", _birthdayForAPI.value!!)
        Log.e("SIGNUP", "${_phoneLada.value!!}${_phone.value!!}")
        Log.e("SIGNUP", _identityImageType.value!!.type)
        Log.e("SIGNUP", _identityImageIne.value!!)
        Log.e("SIGNUP", _identityImageMigrationForm.value!!)
        Log.e("SIGNUP", _identityImagePassport.value!!)
    }

    fun setErrorCode(code: Int){
        _errorResponse.value = code
    }

    fun clearSignUpDtoStateHandle() {
        _userSignupDto.value = UserSignUpDto("", "", "", "", "", "", "", "", "")
    }
}