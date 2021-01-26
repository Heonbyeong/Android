package com.example.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var tiltView: TiltView

    private val sensorManager by lazy{
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) // 화면 켜진상태로 유지
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE // 가로모드 고정
        super.onCreate(savedInstanceState)

        tiltView = TiltView(this)
        setContentView(tiltView)

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                                                    SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d("MainActivity", "Hello")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let{
            Log.d("MainActivity","onSensorChanged: " + "x : ${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")

            tiltView.onSensorEvent(event)
        }
    }
}