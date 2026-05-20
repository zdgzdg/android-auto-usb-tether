package com.example.autotether

import android.util.Log
import rikka.shizuku.ShizukuRemoteProcess

object ShizukuShell {

    fun exec(cmd: String) {

        try {

            val process = ShizukuRemoteProcess(
                arrayOf("sh", "-c", cmd),
                null,
                null
            )

            process.waitFor()

            Log.d("SHIZUKU", "command executed")

        } catch (e: Exception) {

            Log.e("SHIZUKU", e.toString())
        }
    }
}