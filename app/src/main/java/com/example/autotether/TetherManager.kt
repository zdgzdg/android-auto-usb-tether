package com.example.autotether

import android.content.Context
import android.net.ConnectivityManager
import android.os.Handler
import android.util.Log

object TetherManager {

    fun startUsbTether(context: Context) {

        try {

            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager

            val method =
                ConnectivityManager::class.java.getDeclaredMethod(
                    "startTethering",
                    Int::class.javaPrimitiveType,
                    Boolean::class.javaPrimitiveType,
                    ConnectivityManager.OnStartTetheringCallback::class.java,
                    Handler::class.java
                )

            method.isAccessible = true

            method.invoke(
                cm,
                1,
                false,
                object : ConnectivityManager.OnStartTetheringCallback() {

                    override fun onTetheringStarted() {

                        Log.d("TETHER", "USB tether started")
                    }

                    override fun onTetheringFailed() {

                        Log.e("TETHER", "startTethering failed")

                        fallback()
                    }
                },
                null
            )

        } catch (e: Exception) {

            Log.e("TETHER", e.toString())

            fallback()
        }
    }

    private fun fallback() {

        try {

            ShizukuShell.exec(
                "svc usb setFunctions rndis"
            )

        } catch (e: Exception) {

            Log.e("TETHER", "fallback failed")
        }
    }
}