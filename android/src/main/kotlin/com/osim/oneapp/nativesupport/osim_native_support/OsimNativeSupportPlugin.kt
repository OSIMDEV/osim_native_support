package com.osim.oneapp.nativesupport.osim_native_support

import android.content.Context
import com.osim.oneapp.nativesupport.osim_native_support.sleep.AndroidNativeSleepWrapper
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler

/** OsimNativeSupportPlugin */
class OsimNativeSupportPlugin: FlutterPlugin, MethodCallHandler {
  private var baseContext: Context? = null

  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "osim_native_support")
    channel.setMethodCallHandler(this)
    baseContext = flutterPluginBinding.applicationContext
  }

  override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
    result.success(runCatching {
      when (call.method) {
        AndroidNativeSleepWrapper.METHOD_NAME -> baseContext?.apply {
          val pref = OneAppNativeSharedPreference(this, AndroidNativeSleepWrapper.ENTRY)
          pref.write(AndroidNativeSleepWrapper.ARG_KEY, call.arguments<String>())
        }
      }
      true
    }.isSuccess)
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
    baseContext = null
  }
}
