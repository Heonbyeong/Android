package com.example.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            val name = editText.text.toString()

            val intent = Intent(applicationContext, MyService::class.java)
            intent.putExtra("command", "show")
            intent.putExtra("name", name)

            startService(intent)
        }

        val passedIntent = intent
        processIntent(passedIntent)
    }

    override fun onNewIntent(intent: Intent?) {
        if (intent != null) {
            processIntent(intent)
        }
        super.onNewIntent(intent)
    }

    private fun processIntent(intent: Intent) {
        if(intent != null){
            val command = intent.getStringExtra("command")
            val name = intent.getStringExtra("name")

            Toast.makeText(applicationContext, "command : $command , name : $name", Toast.LENGTH_SHORT).show()
        }
    }
}