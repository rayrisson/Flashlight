package com.example.flashlight

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ToggleButton

class MainActivity : AppCompatActivity() {
    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager

        try{
            cameraId = cameraManager.cameraIdList[0]
        }catch (e: CameraAccessException){
            e.printStackTrace()
        }
        var bflash: ToggleButton = findViewById(R.id.flash)
        bflash.setOnCheckedChangeListener { _, isChecked -> onOffFlash(isChecked) }
    }

    private fun onOffFlash(status: Boolean){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraManager.setTorchMode(cameraId, status)
        }
    }
}