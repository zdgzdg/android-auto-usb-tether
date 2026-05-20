package com.example.autotether

import android.util.Log
import rikka.shizuku.Shizuku
import java.io.BufferedReader
import java.io.InputStreamReader

object ShizukuShell {

    fun exec(cmd: String) {

        try {

            val process = Shizuku.newProcess(
                arrayOf("sh", "-c", cmd),
                null,
                null
            )

            val reader = BufferedReader(
                InputStreamReader(process.inputStream)
            )

            val output = reader.readText()

            Log.d("SHIZUKU", output)

        } catch (e: Exception) {

            Log.e("SHIZUKU", e.toString())
        }
    }
}