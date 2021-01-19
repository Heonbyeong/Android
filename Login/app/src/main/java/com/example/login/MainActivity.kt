package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.login.Volley.LoginRequest

import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() { // 로그인 페이지

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val GoSignUp = Intent(this, SignUpActivity::class.java)

        SignUpButton.setOnClickListener{
            startActivity(GoSignUp)
        }

        LoginButton.setOnClickListener {
            if (IdEditText.text.toString() == "" || PasswordEditText.text.toString() == "") {
                Toast.makeText(this, "아이디 또는 패스워드를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else {
                //EditText에 있는 값 가져오기
                val userId = IdEditText.text.toString()
                val userPass = PasswordEditText.text.toString()

                val responseListener  = object : Response.Listener<String>{
                        override fun onResponse(response : String){
                            try {
                                println("hongchul" + response)
                                val jsonObject = JSONObject(response)
                                val success = jsonObject.getBoolean("success")
                                if(success){ // 로그인에 성공한 경우
                                    val userId = jsonObject.getString("userID")
                                    val userPass = jsonObject.getString("userPassword")

                                    Toast.makeText(applicationContext, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                                    val GoSuccess = Intent(applicationContext, SuccessActivity::class.java)
                                    GoSuccess.putExtra("userID", userId)
                                    GoSuccess.putExtra("userPass", userPass)
                                    startActivity(GoSuccess)
                                } else { // 로그인에 실패한 경우
                                    Toast.makeText(applicationContext, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                                    return
                                }
                            } catch (e : JSONException){
                                e.printStackTrace()
                            }
                        }
                }
                val loginRequest = LoginRequest(userId, userPass, responseListener)
                val queue = Volley.newRequestQueue(this)
                queue.add(loginRequest)
            }
        }
    }
}