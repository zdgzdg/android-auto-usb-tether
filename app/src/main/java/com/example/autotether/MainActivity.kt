package com.example.autotether

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import rikka.shizuku.Shizuku

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tv = TextView(this)
        tv.text = "Auto USB Tether Ready"

        setContentView(tv)

        requestShizukuPermission()
    }

    private fun requestShizukuPermission() {

        if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
            return
        }

        Shizuku.requestPermission(0)
    }
}