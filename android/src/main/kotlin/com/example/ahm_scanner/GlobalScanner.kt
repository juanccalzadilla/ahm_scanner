package com.example.ahm_scanner


import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.EventChannel.EventSink


/**
 * HONEYWELL IMPLEMENTATION
 * **/

import com.honeywell.aidc.*

/**
 * ITOS IMPLEMENTATION
 * **/
import scanR.ScanManager

class GlobalScanner internal constructor(ftEng: FlutterEngine, act: Activity) :
    EventChannel.StreamHandler, BarcodeReader.BarcodeListener {

    private var events: EventSink? = null
    private var barcodeReader: BarcodeReader? = null
    private var manager: AidcManager? = null
    private var activity: Activity? = null
    private var itosManager: ScanManager? = null


    init {
        val messenger = ftEng.dartExecutor.binaryMessenger
        val eventChannel = EventChannel(messenger, "com.ahorramas.plugins/global_scanner")
        eventChannel.setStreamHandler(this)
        this.activity = act
    }


    override fun onListen(arguments: Any?, events: EventSink?) {
        this.events = events
        startListenBarcodes()
    }

    override fun onCancel(arguments: Any?) {
        this.disposeListeners()
    }


    private fun startListenBarcodes() {
        val brand: String = Build.BRAND

        when (brand) {
            "Honeywell" -> {
                this.startListenHoneywell()
            }

            "ITOS" -> {
                itosManager?.barcodeReceiveModel = 2
                this.startListenITOS()
            }

            else -> {
                events?.error(
                    "BRAND_NOT_IMPLEMENTED",
                    "This brand SDK has not been implemented.",
                    "You must develop a new brand implementation"
                )
            }
        }

    }

    private fun startListenITOS() {
        val filterITOS = IntentFilter()
        filterITOS.addAction("com.barcode.sendBroadcast")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            this.activity?.registerReceiver(
                itosBroadcast,
                filterITOS,
                Context.RECEIVER_NOT_EXPORTED
            )
        }
    }

    private fun startListenHoneywell() {
        AidcManager.create(activity) { param ->

            this.manager = param
            this.barcodeReader = manager?.createBarcodeReader()

            if (this.barcodeReader != null) {
                this.barcodeReader!!.claim()

                barcodeReader?.setProperty(BarcodeReader.PROPERTY_EAN_8_ENABLED, true)
                barcodeReader?.setProperty(
                    BarcodeReader.PROPERTY_EAN_8_CHECK_DIGIT_TRANSMIT_ENABLED,
                    true
                )
                barcodeReader?.setProperty(BarcodeReader.PROPERTY_EAN_13_ENABLED, true)
                barcodeReader?.setProperty(
                    BarcodeReader.PROPERTY_EAN_13_CHECK_DIGIT_TRANSMIT_ENABLED,
                    true
                )
                barcodeReader?.setProperty(BarcodeReader.PROPERTY_UPC_A_ENABLE, true)
                barcodeReader?.setProperty(
                    BarcodeReader.PROPERTY_UPC_A_CHECK_DIGIT_TRANSMIT_ENABLED,
                    true
                )
                barcodeReader?.setProperty(BarcodeReader.PROPERTY_UPC_E_ENABLED, true)
                barcodeReader?.setProperty(
                    BarcodeReader.PROPERTY_UPC_E_CHECK_DIGIT_TRANSMIT_ENABLED,
                    true
                )
                barcodeReader?.setProperty(BarcodeReader.PROPERTY_TRIGGER_AUTO_MODE_TIMEOUT, 3)
            }

            barcodeReader?.addBarcodeListener(this)

        }
    }

    override fun onBarcodeEvent(p0: BarcodeReadEvent?) {
        activity?.runOnUiThread {
            if(p0 != null) {
                sendTerminalVibration()
                events?.success(p0?.barcodeData)
            }
        }
    }

    override fun onFailureEvent(p0: BarcodeFailureEvent?) {
        activity?.runOnUiThread {

            if (p0?.source.toString().contains("BarcodeReader")){
                events?.success("")
            }else{
                events?.error("UNEXPECTED_ERROR", "An unexpected error has been throw", "")
            }
        }
    }


    private val itosBroadcast = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val data = intent?.getStringExtra("BARCODE")

            if (data != null) {
                activity?.runOnUiThread {
                    sendTerminalVibration()
                    events?.success(data)
                }
            }
        }
    }

    fun sendTerminalVibration(milliseconds: Long = 100) {

        val vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    milliseconds,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(milliseconds)
        }
    }

    fun disposeListeners() {
        if (barcodeReader != null) {
            barcodeReader?.removeBarcodeListener(this)
        }
    }

    fun setUpITOSScanner() {
        val brand: String = Build.BRAND
        if (brand == "ITOS") {
            itosManager = ScanManager.getInstance(this.activity)
            itosManager?.scanSwitchRight = true
            itosManager?.scanSwitchRight = true
            itosManager?.scanSwitchMiddle = true
            itosManager?.barcodeReceiveModel = 2 //0-fast 1-slow 2-broadcast
        }
    }


    fun pauseITOSScanner() {
        val brand: String = Build.BRAND
        if (brand == "ITOS") {
            itosManager?.barcodeReceiveModel = 1 //0-fast 1-slow 2-broadcast
        }

    }

}
