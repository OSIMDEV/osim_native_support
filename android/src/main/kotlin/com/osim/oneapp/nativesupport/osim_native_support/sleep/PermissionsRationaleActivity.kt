package com.osim.oneapp.nativesupport.osim_native_support.sleep

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebStorage
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.iwdael.immersive.setContentView
import com.osim.oneapp.nativesupport.osim_native_support.OneAppNativeSharedPreference
import com.osim.oneapp.nativesupport.osim_native_support.R
import com.scwang.smart.refresh.layout.api.RefreshLayout

class PermissionsRationaleActivity: AppCompatActivity() {
    private val loading: LottieAnimationView by lazy { findViewById(R.id.loading) }
    private val rlRefresh: RefreshLayout by lazy { findViewById(R.id.fl_refresh) }
    private val tvTitle: TextView by lazy { findViewById(R.id.tv_title) }
    private val webContent: WebView by lazy { findViewById(R.id.web_content) }

    private var pushDown = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_permissions_rationale,
            android.R.color.transparent,
            android.R.color.transparent,
            hideStatusBar = false,
            hideNavigationBar = false
        )
        initWebContent()
        val pref = OneAppNativeSharedPreference(baseContext, AndroidNativeSleepWrapper.ENTRY)
        webContent.loadUrl(pref.read(AndroidNativeSleepWrapper.ARG_KEY, ""))
        rlRefresh.setOnRefreshListener {
            pushDown = true
            webContent.reload()
        }
        tvTitle.setTextIsSelectable(true)
        findViewById<View>(R.id.iv_close).setOnClickListener { finish() }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebContent() = webContent.apply {
        with(settings) {
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = false
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    try {
                        tvTitle.text = url
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                    startLoadingAnim()
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    rlRefresh.finishRefresh(true)
                    pushDown = false
                    endLoadingAnim()
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    rlRefresh.finishRefresh(false)
                    pushDown = false
                    endLoadingAnim()
                }
            }
            webChromeClient = object : WebChromeClient() {

            }
        }
    }

    private fun startLoadingAnim() {
        if (!pushDown) loading.playAnimation()
        loading.visibility = if (pushDown) View.GONE else View.VISIBLE
    }

    private fun endLoadingAnim() {
        loading.cancelAnimation()
        loading.visibility = View.GONE
    }

    @Deprecated("Deprecated in Java", ReplaceWith("onBackPressedDispatcher.onBackPressed()"))
    override fun onBackPressed() {
        if (webContent.canGoBack()) {
            webContent.goBack()
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webContent.apply {
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