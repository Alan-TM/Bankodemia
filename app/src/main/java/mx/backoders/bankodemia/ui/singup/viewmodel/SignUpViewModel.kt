package mx.backoders.bankodemia.ui.singup.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mx.backoders.bankodemia.common.utils.timeStampForImage
import java.io.File
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class SignUpViewModel : ViewModel() {

    private val _decodeImage = MutableLiveData<String>()
    val decodeImage: LiveData<String> = _decodeImage

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
}