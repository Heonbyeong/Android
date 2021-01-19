package com.example.login

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.login.R
import kotlinx.android.synthetic.main.success_page.*

class SuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success_page)

        val intent = getIntent()
        var userID = intent.getStringExtra("userID")
        var userPass = intent.getStringExtra("userPass")

        idText.setText(userID)
        passText.setText(userPass)
    }
}