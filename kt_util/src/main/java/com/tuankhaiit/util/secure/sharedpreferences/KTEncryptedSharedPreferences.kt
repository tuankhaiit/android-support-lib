package com.tuankhaiit.util.secure.sharedpreferences

import android.content.Context

class KTEncryptedSharedPreferences private constructor(
    context: Context,
    private val sharedPreferences: KTSharedPreferences = KTEncryptedSharedPreferencesDefault(context)
) : KTSharedPreferences {

    override fun remove(key: String) = sharedPreferences.remove(key)

    override fun contains(key: String?): Boolean = sharedPreferences.contains(key)

    override fun clear() = sharedPreferences.clear()

    override fun getAll(): MutableMap<String, *> = sharedPreferences.getAll()

    override fun putBoolean(key: String, value: Boolean) = sharedPreferences.putBoolean(key, value)

    override fun getBoolean(key: String?, defValue: Boolean): Boolean =
        sharedPreferences.getBoolean(key, defValue)

    override fun putFloat(key: String, value: Float) = sharedPreferences.putFloat(key, value)

    override fun getFloat(key: String?, defValue: Float): Float =
        sharedPreferences.getFloat(key, defValue)

    override fun putInt(key: String, value: Int) = sharedPreferences.putInt(key, value)

    override fun getInt(key: String?, defValue: Int): Int = sharedPreferences.getInt(key, defValue)

    override fun putLong(key: String, value: Long) = sharedPreferences.putLong(key, value)

    override fun getLong(key: String?, defValue: Long): Long =
        sharedPreferences.getLong(key, defValue)

    override fun putString(key: String, value: String?) = sharedPreferences.putString(key, value)

    override fun getString(key: String?, defValue: String?): String? =
        sharedPreferences.getString(key, defValue)

    override fun putStringSet(key: String, values: Set<String>?) =
        sharedPreferences.putStringSet(key, values)

    override fun getStringSet(key: String?, defValues: MutableSet<String>?): MutableSet<String>? =
        sharedPreferences.getStringSet(key, defValues)

    inline fun <reified D : Any?> put(key: String, value: D?) {
        when (value) {
            is Boolean -> putBoolean(key, value)
            is Float -> putFloat(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            else -> putObject(key, value)
        }
    }

    inline fun <reified T> get(key: String): T? {
        return when (T::class.java) {
            Boolean::javaClass -> getBoolean(key, false) as T?
            Float::javaClass -> getFloat(key, 0.0f) as T?
            Int::javaClass -> getInt(key, -1) as T?
            Long::javaClass -> getLong(key, -1) as T?
            String::javaClass -> getString(key, null) as T?
            else -> getObject<T>(key)
        }
    }

    inline fun <reified T> putObject(key: String, obj: T?) {
        try {
            putString(key, KTSharedPreferencesUtils.objectToString(obj))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inline fun <reified T> getObject(key: String): T? {
        return try {
            KTSharedPreferencesUtils.stringToObject(getString(key, null)) as T?
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    companion object {

        fun get(context: Context, name: String? = null): KTEncryptedSharedPreferences {
            return if (name.isNullOrEmpty()) {
                KTEncryptedSharedPreferences(context)
            } else {
                KTEncryptedSharedPreferences(
                    context,
                    KTEncryptedSharedPreferencesDefault(context, name)
                )
            }
        }
    }
}