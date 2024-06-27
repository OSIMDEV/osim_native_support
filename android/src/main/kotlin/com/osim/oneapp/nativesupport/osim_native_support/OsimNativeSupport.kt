package com.osim.oneapp.nativesupport.osim_native_support

import android.app.Application
import android.util.Log
import com.blankj.utilcode.util.Utils

object OsimNativeSupport {
    fun init(app: Application) {
        Utils.init(app)
    }
}