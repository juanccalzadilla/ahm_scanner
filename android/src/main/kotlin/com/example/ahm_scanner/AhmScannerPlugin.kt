package com.example.ahm_scanner

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.AppOpsManagerCompat
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

class AhmScannerPlugin : FlutterPlugin, ActivityAware {

  private var globalScanner: GlobalScanner? = null
  private var activity: Activity? = null
  private var flutterEngine: FlutterEngine? = null

  override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    flutterEngine = flutterPluginBinding.flutterEngine
  }

  override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
    globalScanner?.pauseITOSScanner()
  }


  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity
    activity?.application?.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
      override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}
      override fun onActivityStarted(activity: Activity) {}
      override fun onActivityResumed(activity: Activity) {
        if (activity == this@AhmScannerPlugin.activity) {
          globalScanner?.setUpITOSScanner()
        }
      }
      override fun onActivityPaused(activity: Activity) {
        if (activity == this@AhmScannerPlugin.activity) {
          globalScanner?.pauseITOSScanner()
        }
      }
      override fun onActivityStopped(activity: Activity) {
        if (activity == this@AhmScannerPlugin.activity) {

        }
      }
      override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

      override fun onActivityDestroyed(activity: Activity) {
        if (activity == this@AhmScannerPlugin.activity) {
          globalScanner?.disposeListeners()
          globalScanner?.pauseITOSScanner()
        }
      }
    })

    activity?.let {
      flutterEngine?.let { engine ->
        globalScanner = GlobalScanner(engine, it)
      }
    }
  }
  override fun onDetachedFromActivityForConfigChanges() {
    activity = null
    globalScanner = null
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    activity = binding.activity
    activity?.let {
      flutterEngine?.let { engine ->
        globalScanner = GlobalScanner(engine, it)
      }
    }
  }

  override fun onDetachedFromActivity() {
    activity = null
    globalScanner?.pauseITOSScanner()
  }


}

