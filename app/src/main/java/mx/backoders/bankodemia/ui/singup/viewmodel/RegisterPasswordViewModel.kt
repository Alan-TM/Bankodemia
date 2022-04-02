package mx.backoders.bankodemia.ui.singup.viewmodel

import androidx.lifecycle.*
import mx.backoders.bankodemia.common.utils.PasswordError
import mx.backoders.bankodemia.common.utils.PasswordError.*

class RegisterPasswordViewModel : ViewModel() {
    private val _isValidConsecutiveCharacters = MutableLiveData<Boolean>()
    private val _isValidMinLength = MutableLiveData<Boolean>()
    private val _isValidRepeatedCharacters = MutableLiveData<Boolean>()
    private val _isSamePassword = MutableLiveData<Boolean>()
    private val _isEmptyPassword = MutableLiveData<Boolean>()
    private val _isEmptyPasswordConfirmation = MutableLiveData<Boolean>()

    val mediatorPasswordErrorLiveData = MediatorLiveData<PasswordError>()
    val mediatorPasswordConfirmErrorLiveData = MediatorLiveData<PasswordError>()

    fun setupMediator(){
        mediatorPasswordErrorLiveData.addSource(_isValidConsecutiveCharacters) {
            mediatorPasswordErrorLiveData.value = if (it) NONE else CONSECUTIVE_CHARACTERS
        }

        mediatorPasswordErrorLiveData.addSource(_isValidMinLength){
            mediatorPasswordErrorLiveData.value = if(it) NONE else MIN_LENGTH
        }

        mediatorPasswordErrorLiveData.addSource(_isEmptyPassword){
            mediatorPasswordErrorLiveData.value = if(it) NONE else EMPTY
        }

        mediatorPasswordErrorLiveData.addSource(_isValidRepeatedCharacters){
            mediatorPasswordErrorLiveData.value = if(it) NONE else REPEATED_CHARACTERS
        }

        mediatorPasswordConfirmErrorLiveData.addSource(_isEmptyPasswordConfirmation){
            mediatorPasswordConfirmErrorLiveData.value = if(it) NONE else EMPTY
        }

        mediatorPasswordConfirmErrorLiveData.addSource(_isSamePassword){
            mediatorPasswordConfirmErrorLiveData.value = if(it) NONE else NOT_MATCHING
        }
    }

    fun isValidConsecutivePassword(password: String) {
        _isValidConsecutiveCharacters.value =
            consecutiveCharacterPassword(password) && consecutiveNumbersPassword(password)
    }

    fun isValidRepeatedCharacters(password: String){
        _isValidRepeatedCharacters.value = repeatedCharactersPassword(password)
    }

    fun minLengthPassword(password: String) {
        _isValidMinLength.value = password.length >= 6
    }

    fun isEmptyPassword(password: String){
        _isEmptyPassword.value = password.isNotEmpty()
    }

    fun isEmptyPasswordConfirmation(password_confirm: String){
        _isEmptyPasswordConfirmation.value = password_confirm.isNotEmpty()
    }

    fun isSamePassword(password: String, password_confirmation: String) {
        if (password.isNotEmpty() && password.isNotBlank())
            _isSamePassword.value = password_confirmation == password
    }

    private fun consecutiveNumbersPassword(password: String): Boolean {
        var index = 0
        val length = password.length - 1
        if (length == -1) {
            return true
        } else {
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

    private fun consecutiveCharacterPassword(password: String): Boolean {
        val length = password.length - 1
        if (length >= 1) {
            for ((index, value) in password.withIndex()) {
                if (index < length) {
                    if (value in 'a'..'z' && password[index + 1] in 'a'..'z') {
                        val valueToDigit = value.code
                        val nextValueToDigit = password[index + 1].code

                        if (valueToDigit + 1 == nextValueToDigit || valueToDigit - 1 == nextValueToDigit) {
                            return false
                        }
                    }
                }
            }
        }
        return true
    }

    private fun repeatedCharactersPassword(password: String): Boolean {
        if (password.length - 1 >= 0) {
            var count = 0
            outer@ for (i in password.indices) {
                count = 1
                if (password[i] in 'a'..'z') {
                    for (j in i + 1 until password.length) {
                        if (password[i] == password[j] && password[i] != ' ') {
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