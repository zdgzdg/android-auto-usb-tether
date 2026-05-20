package com.example.autotether

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class UsbReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent?.action == Intent.ACTION_POWER_CONNECTED) {

            Log.d("USB", "USB connected")

            context?.let {

                TetherManager.startUsbTether(it)
            }
        }
    }
}