package mx.backoders.bankodemia.common.preferences

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import mx.backoders.bankodemia.common.model.login.UserLoginResponse

object SharedPreferencesInstance {
    val sharedPref = SharedPreferencesInstance
    lateinit var sharedPreferences : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    private val TAG = "SharedPreferences"

    fun getInstance(context: Context): SharedPreferencesInstance {
        sharedPreferences = context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
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

    fun saveSession(sesion: UserLoginResponse){
        editor.putString("token",sesion.token)
        editor.putString("expiresIn",sesion.expiresIn)
        editor.apply()
    }

    fun getSesion():UserLoginResponse{
        return UserLoginResponse(
            sharedPreferences.getString("token",""),
            sharedPreferences.getString("expiresIn","")
        )
    }
}