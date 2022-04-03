package mx.backoders.bankodemia.ui.singup.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import mx.backoders.bankodemia.common.utils.IdentityImageType
import mx.backoders.bankodemia.common.utils.IdentityImageType.*
import mx.backoders.bankodemia.common.utils.timeStampForImage
import java.io.File
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class SignUpViewModel(stateHandle: SavedStateHandle) : ViewModel() {

    val _identityImageType = stateHandle.getLiveData("identityImageType", INE)
    private val _decodeImage = stateHandle.getLiveData("decodeImage", "")
    val decodeImage: LiveData<String> = _decodeImage
    private val _identityImageIne = stateHandle.getLiveData("INE", "")
    val identityImageIne: LiveData<String> = _identityImageIne
    private val _identityImageMigrationForm = stateHandle.getLiveData("MIGRATION_FORM", "")
    val identityImageMigrationForm: LiveData<String> = _identityImageMigrationForm
    private val _identityImagePassport = stateHandle.getLiveData("PASSPORT", "")
    val identityImagePassport: LiveData<String> = _identityImagePassport

    private val _password = stateHandle.getLiveData("password", "")
    val password: LiveData<String> = _password

    /*fun decodeImageForAPI(image: File){
        _decodeImage.value = Base64.getEncoder().encodeToString(image.absolutePath.toByteArray())
    }*/

    fun decodeImageForAPI(image: File): String = Base64.getEncoder().encodeToString(image.absolutePath.toByteArray())

    fun createTimeStampForImage(): String = timeStampForImage()

    fun setIdentityImageType(identityType: IdentityImageType){
        _identityImageType.value = identityType
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
}