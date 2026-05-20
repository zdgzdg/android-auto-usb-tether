package com.example.autotether

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

object TetherManager {

    fun startUsbTether(context: Context) {

        try {

            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager

            val clazz = Class.forName(
                "android.net.ConnectivityManager"
            )

            val callbackClass = Class.forName(
                "android.net.ConnectivityManager\$OnStartTetheringCallback"
            )

            val method = clazz.getDeclaredMethod(
                "startTethering",
                Int::class.javaPrimitiveType,
                Boolean::class.javaPrimitiveType,
                callbackClass,
                android.os.Handler::class.java
            )

            method.isAccessible = true

            method.invoke(
                cm,
                1,
                false,
                null,
                null
            )

            Log.d("TETHER", "startTethering invoked")

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