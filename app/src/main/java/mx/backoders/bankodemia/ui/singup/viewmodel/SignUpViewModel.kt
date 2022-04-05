package mx.backoders.bankodemia.ui.singup.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import mx.backoders.bankodemia.common.utils.IdentityImageType
import mx.backoders.bankodemia.common.utils.IdentityImageType.*
import mx.backoders.bankodemia.common.utils.parseBirthdayForAPI
import mx.backoders.bankodemia.common.utils.parseBirthdayForView
import mx.backoders.bankodemia.common.utils.timeStampForImage
import java.io.File
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
    val birthdayForAPI: LiveData<String> = _birthdayForAPI

    private val _identityImageType = stateHandle.getLiveData("identityImageType", INE)
    private val _identityImageIne = stateHandle.getLiveData("INE", "")
    val identityImageIne: LiveData<String> = _identityImageIne
    private val _identityImageMigrationForm = stateHandle.getLiveData("MIGRATION_FORM", "")
    val identityImageMigrationForm: LiveData<String> = _identityImageMigrationForm
    private val _identityImagePassport = stateHandle.getLiveData("PASSPORT", "")
    val identityImagePassport: LiveData<String> = _identityImagePassport

    private val _password = stateHandle.getLiveData("password", "")
    val password: LiveData<String> = _password

    fun decodeImageForAPI(image: File): String = Base64.getEncoder().encodeToString(image.absolutePath.toByteArray())

    fun createTimeStampForImage(): String = timeStampForImage()

    fun setIdentityImageType(identityType: IdentityImageType){
        _identityImageType.value = identityType
    }

    fun setUserEmail(email: String){
        _email.value = email
        Log.e("EMAIL", _email.value!!)
    }

    fun setUserPhone(phone: String){
        _phone.value = phone
    }

    fun setUserPhoneLada(lada: String){
        _phoneLada.value = lada
    }

    fun setUserPassword(password: String){
        _password.value = password
    }

    fun setIdentityImage(image: String){
        when(_identityImageType.value){
            INE -> _identityImageIne.value = image
            PASSPORT -> _identityImagePassport.value = image
            else -> _identityImageMigrationForm.value = image
        }
    }

    fun setUserPersonalInfo(name: String, lastName: String, occupation: String){
        if(name.isNotBlank())
            _name.value = name
        if(lastName.isNotBlank())
            _lastName.value = lastName
        if(occupation.isNotBlank())
            _occupation.value = occupation
    }

    fun setBirthdayParsers(day: Int, month: Int, year: Int){
        _birthdayForView.value = parseBirthdayForView(day, month, year)
        _birthdayForAPI.value = parseBirthdayForAPI(day, month, year)
    }
}