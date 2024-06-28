package com.osim.oneapp.nativesupport.osim_native_support

import android.app.Application
import com.blankj.utilcode.util.Utils

class OsimNativeSupport {
    companion object {
        fun init(app: Application) {
            Utils.init(app)
        }
    }
}