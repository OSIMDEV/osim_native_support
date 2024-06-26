package com.osim.oneapp.nativesupport.osim_native_support

import android.content.Context
import android.content.SharedPreferences

class OneAppNativeSharedPreference(context: Context, entry: String) {
    private var prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(entry, Context.MODE_PRIVATE)
    }

    fun <T> read(key: String, defVal: T): T = prefs.let {
        when (defVal) {
            is Int -> cast(it.getInt(key, defVal), defVal = defVal)
            is Long -> cast(it.getLong(key, defVal), defVal = defVal)
            is String -> cast(it.getString(key, defVal), defVal = defVal)
            is Float -> cast(it.getFloat(key, defVal), defVal = defVal)
            is Boolean -> cast(it.getBoolean(key, defVal), defVal = defVal)
            else -> defVal
        }
    }

    fun <T> write(key: String, value: T) = prefs.edit().apply {
        when (value) {
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is String -> putString(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
        }
    }.commit()

    private inline fun <reified T> cast(value: Any?, defVal: T): T {
        return if (value is T) {
            value
        } else {
            defVal
        }
    }
}