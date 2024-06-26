package com.osim.oneapp.nativesupport.osim_native_support.sleep

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebStorage
import android.webkit.WebViewClient
import androidx.fragment.app.FragmentActivity
import com.osim.oneapp.nativesupport.osim_native_support.OneAppNativeSharedPreference
import com.osim.oneapp.nativesupport.osim_native_support.databinding.ActivityPermissionRationaleBinding

class PermissionsRationaleActivity: FragmentActivity() {
    private lateinit var binding: ActivityPermissionRationaleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionRationaleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWebContent()
        val pref = OneAppNativeSharedPreference(baseContext, AndroidNativeSleepWrapper.ENTRY)
        binding.webContent.loadUrl(pref.read(AndroidNativeSleepWrapper.ARG_KEY, ""))
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebContent() = binding.webContent.apply {
        with(settings) {
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            webViewClient = object : WebViewClient() {

            }
            webChromeClient = object : WebChromeClient() {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webContent.apply {
            clearMatches()
            clearHistory()
            clearFormData()
            clearSslPreferences()
            clearCache(true)
        }
        CookieManager.getInstance().removeAllCookies(null)
        WebStorage.getInstance().deleteAllData()
    }
}