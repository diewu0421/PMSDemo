package com.example.pmsdemo

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
        }

    }

    fun testInstall(view: View) {
        silentInstall("com.example.wmsdemo", "/sdcard/app-debug.apk")
    }
    companion object {
        const val TAG = "MainActivity"
        fun silentInstall(pkgName: String, apkAbsolutePath: String): Boolean {
            var isSuccess = false
            val args = arrayOf("pm", "install", "-r", "-d", "-i", pkgName, "--user", "0", apkAbsolutePath)
            val processBuilder = ProcessBuilder(*args)
            var process: Process? = null
            var inIs: InputStream? = null
            try {
                val baos = ByteArrayOutputStream()
                process = processBuilder.start()
                baos.write("/".toByteArray())
                inIs = process.inputStream
                val b = ByteArray(1024)
                while (inIs.read(b) !== -1) {
                    baos.write(b)
                }
                val res = String(baos.toByteArray(), Charset.defaultCharset())
                isSuccess = res.contains("Success")
                baos.close()
            } catch (e: Exception) {
                Log.i(TAG, "silentInstall end$e")
                e.printStackTrace()
            } finally {
                try {
                    inIs?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                process?.destroy()
            }
            Log.i(TAG, "silentInstall end isSuccess$isSuccess")
            return isSuccess
        }

    }

    fun testAccess(view: View) {

        if (!MyAccessbilityService.isStart()) {
            try {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            } catch (e: java.lang.Exception) {
                startActivity(Intent(Settings.ACTION_SETTINGS))
                e.printStackTrace()
            }
        }
    }
}