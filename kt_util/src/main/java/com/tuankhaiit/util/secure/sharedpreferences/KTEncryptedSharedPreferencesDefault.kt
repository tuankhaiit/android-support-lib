package com.tuankhaiit.util.secure.sharedpreferences

import android.content.Context

class KTEncryptedSharedPreferencesDefault(context: Context, fileName: String = FILE_NAME) : KTSharedPreferences {

    private val preferences = KTSharedPreferencesUtils.getSharedPreferences(context, fileName)

    override fun remove(key: String) = preferences.edit().remove(key).apply()

    override fun contains(key: String?): Boolean = preferences.contains(key)

    override fun clear() = preferences.edit().clear().apply()

    override fun getAll(): MutableMap<String, *> = preferences.all

    override fun putBoolean(key: String, value: Boolean) =
        preferences.edit().putBoolean(key, value).apply()

    override fun getBoolean(key: String?, defValue: Boolean): Boolean =
        preferences.getBoolean(key, defValue)

    override fun putFloat(key: String, value: Float) =
        preferences.edit().putFloat(key, value).apply()

    override fun getFloat(key: String?, defValue: Float): Float =
        preferences.getFloat(key, defValue)

    override fun putInt(key: String, value: Int) = preferences.edit().putInt(key, value).apply()

    override fun getInt(key: String?, defValue: Int): Int = preferences.getInt(key, defValue)

    override fun putLong(key: String, value: Long) = preferences.edit().putLong(key, value).apply()

    override fun getLong(key: String?, defValue: Long): Long = preferences.getLong(key, defValue)

    override fun putString(key: String, value: String?) =
        preferences.edit().putString(key, value).apply()

    override fun getString(key: String?, defValue: String?): String? =
        preferences.getString(key, defValue)

    override fun putStringSet(key: String, values: Set<String>?) =
        preferences.edit().putStringSet(key, values).apply()

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? =
        preferences.getStringSet(key, defValues)

    companion object {
        private const val FILE_NAME = "KT_encrypted_shared_preferences_default"
    }

}