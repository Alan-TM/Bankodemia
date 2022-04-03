package mx.backoders.bankodemia.ui.singup.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import mx.backoders.bankodemia.common.utils.IdentityImageType
import mx.backoders.bankodemia.common.utils.timeStampForImage
import java.io.File
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class SignUpViewModel(stateHandle: SavedStateHandle) : ViewModel() {

    private val _identityImageType = stateHandle.getLiveData("identityImageType", IdentityImageType.INE)
    private val _decodeImage = stateHandle.getLiveData("decodeImage", "")
    val decodeImage: LiveData<String> = _decodeImage

    private val _password = stateHandle.getLiveData("password", "")
    val password: LiveData<String> = _password

    fun decodeImageForAPI(image: File){
        val imageString: String = Base64.getEncoder().encodeToString(image.absolutePath.toByteArray())
        _decodeImage.value = imageString
    }

    fun createTimeStampForImage(): String = timeStampForImage()

    fun clearDecodeImage(){
        if(!_decodeImage.value.isNullOrBlank()){
            _decodeImage.value = ""
        }
    }

    fun setIdentityImageType(identityType: IdentityImageType){
        _identityImageType.value = identityType
    }

    fun setUserPassword(password: String){
        _password.value = password
    }
}