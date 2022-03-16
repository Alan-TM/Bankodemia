package mx.backoders.bankodemia.common.preferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import mx.backoders.bankodemia.common.model.login.UserLoginResponse

private const val TAG = "SharedPreferences"

object SharedPreferencesInstance {
    private val sharedPref = SharedPreferencesInstance
    lateinit var sharedPreferences : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    fun getInstance(context: Context): SharedPreferencesInstance {
        sharedPreferences = EncryptedSharedPreferences.create(
            context.packageName,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        editor = sharedPreferences.edit()
        return sharedPref
    }

    fun clearAllPreferences(){
        editor.clear().apply()
        Log.d(TAG, "clean")
    }

    fun delete(key: String){
        if(sharedPreferences.contains(key))
            editor.remove(key).apply()
        else
            Log.e(TAG, "this key doesn't exist in your preferences")
    }

    fun getPreference(key: String): String?{
        return if(sharedPreferences.contains(key))
            sharedPreferences.getString(key, null)
        else
            null
    }

    fun saveSession(session: UserLoginResponse){
        editor.putString("token",session.token)
        editor.putString("expiresIn",session.expiresIn)
        editor.apply()
    }

    fun getSession():UserLoginResponse{
        return UserLoginResponse(
            sharedPreferences.getString("token",""),
            sharedPreferences.getString("expiresIn","")
        )
    }
}