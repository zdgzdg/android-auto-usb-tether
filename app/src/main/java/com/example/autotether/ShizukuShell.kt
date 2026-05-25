package com.example.autotether

import android.util.Log

object ShizukuShell {

    fun exec(cmd: String) {

        try {

            val process = Runtime.getRuntime().exec(
                arrayOf("sh", "-c", cmd)
            )

            process.waitFor()

            Log.d("SHIZUKU", "command executed")

        } catch (e: Exception) {

            Log.e("SHIZUKU", e.toString())
        }
    }
}