package com.osim.oneapp.nativesupport.osim_native_support.sleep

import java.util.Locale

class AndroidNativeSleepWrapper {
    companion object {
        const val ENTRY = "sleep"
        val METHOD_NAME = appendPrefix(key = "saveUri")
        const val ARG_KEY = "uri"

        private fun appendPrefix(prefix: String = ENTRY, key: String): String = arrayOf(prefix,
            key.replaceFirstChar {
                if (it.isLowerCase())
                    it.titlecase(Locale.getDefault())
                else
                    it.toString()
            }).joinToString(separator = "")
    }
}