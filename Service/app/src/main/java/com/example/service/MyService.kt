package com.example.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.lang.Exception

class MyService : Service() {
    val TAG = "MyService"
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate() 호출됨")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand() 호출됨")

        if(intent == null){
            return Service.START_STICKY
        } else{
            processCommand(intent)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    private fun processCommand(intent: Intent){
        val command = intent.getStringExtra("command")
        val name = intent.getStringExtra("name")

        Log.d(TAG, "command : $command , name : $name")

        for(i in 0 .. 5){
            try{
                Thread.sleep(1000)
            }catch (e: Exception){}
            Log.d(TAG, "Waiting $i seconds")
        }

        val showIntent = Intent(applicationContext, MainActivity::class.java)

        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        showIntent.putExtra("command", "show")
        showIntent.putExtra("name", "$name from service.")
        startActivity(showIntent)
    }
}