package com.tuankhaiit.util.secure.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object KTSharedPreferencesUtils {

    private fun createOrGetMasterKeys(context: Context): MasterKey {
        return MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    fun getSharedPreferences(context: Context, fileName: String): SharedPreferences {
        return EncryptedSharedPreferences.create(
            context,
            fileName,
            createOrGetMasterKeys(context),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    inline fun <reified T> objectToString(obj: T?): String? {
        var encoded = ""
        try {
            val type = object : TypeToken<T>() {}.type
            encoded = GsonBuilder().create().toJson(obj)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return encoded
    }

    inline fun <reified T> stringToObject(str: String?): T? {
        var obj: T? = null
        try {
            val type = object : TypeToken<T>() {}.type
            obj = GsonBuilder().create().fromJson(str, type)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return obj
    }
}