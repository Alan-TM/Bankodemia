package mx.backoders.bankodemia

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterPasswordViewModel : ViewModel() {
    //should do ENUM class to send error data more accurately
    var isValid = MutableLiveData<Boolean>()

    //this function should be call in the register button
    fun isValidPassword(password: String) {
        val consecutiveNumbers = consecutiveNumbersPassword(password)
        val repeatedCharacters = repeatedCharactersPassword(password)

        isValid.postValue(consecutiveNumbers && repeatedCharacters)
    }

    fun minLengthPassword(password: String) = isValid.postValue(password.length >= 6)

    private fun consecutiveNumbersPassword(password: String): Boolean {
        var index = 0
        val length = password.length - 1
        Log.d("consecutiveNumber", password.indices.toString())
        if(length == -1){
            isValid.postValue(true)
        }else {
            if (length >= 1) {
                while (index < length) {
                    if (password[index] in '0'..'9' && password[index + 1] in '0'..'9') {
                        val charToInt = password[index].digitToInt()
                        val nextToInt = password[index + 1].digitToInt()

                        return !(charToInt < nextToInt && charToInt + 1 == nextToInt || charToInt > nextToInt && charToInt - 1 == nextToInt)
                    }
                    index++
                }
            }
        }
        return true
    }

    private fun repeatedCharactersPassword(password: String): Boolean{
        if(password.length-1 >= 0) {
            var count = 0
            outer@ for (i in password.indices) {
                count = 1
                if(password[i] in 'a'..'z') {
                    for (j in i + 1 until password.length) {
                        if (password[i] == password[j] && password[i] != ' ') {
                            Log.d("REPEATED", "${password[i]} ${password[j]}")
                            count++
                            break@outer
                        }
                    }
                }
            }
            return count == 1
        }
        return true
    }
}