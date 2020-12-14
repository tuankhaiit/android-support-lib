package com.tuankhaiit.util.secure.sharedpreferences

import androidx.annotation.NonNull
import androidx.annotation.Nullable

interface KTSharedPreferences {

    fun remove(key: String)

    fun contains(@Nullable key: String?): Boolean

    fun clear()

    @NonNull
    fun getAll(): MutableMap<String, *>

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(
        @Nullable key: String?,
        defValue: Boolean
    ): Boolean

    fun putFloat(key: String, value: Float)

    fun getFloat(
        @Nullable key: String?,
        defValue: Float
    ): Float

    fun putInt(key: String, value: Int)

    fun getInt(
        @Nullable key: String?,
        defValue: Int
    ): Int

    fun putLong(key: String, value: Long)

    fun getLong(
        @Nullable key: String?,
        defValue: Long
    ): Long

    fun putString(key: String, @Nullable value: String?)

    @Nullable
    fun getString(
        @Nullable key: String?,
        @Nullable defValue: String?
    ): String?

    fun putStringSet(key: String, @Nullable values: Set<String>?)

    @Nullable
    fun getStringSet(
        @Nullable key: String?,
        @Nullable defValues: MutableSet<String>?
    ): MutableSet<String>?
}
