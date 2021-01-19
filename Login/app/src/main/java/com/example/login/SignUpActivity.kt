package com.example.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.example.login.Volley.RegisterRequest
import kotlinx.android.synthetic.main.sign_up_page.*
import org.json.JSONException
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() { // 회원가입 페이지
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up_page)

        SignUpBtn.setOnClickListener{
            val userID = EmailEditText.text.toString()
            val userPass = SignUpPasswordEdit.text.toString()
            val userPassOverlap = SignUpPasswordOverlap.text.toString()
            val userName = NameEditText.text.toString()

            val responseListener = object : Response.Listener<String> {
                override fun onResponse(response: String){
                    try {
                        val jsonObject = JSONObject(response)
                        val success = jsonObject.getBoolean("success")
                        if(success && userPass.equals(userPassOverlap)){ // 회원 등록에 성공한 경우
                            Toast.makeText(applicationContext, "회원 등록에 성공했습니다.", Toast.LENGTH_SHORT).show()
                            val GoLogin = Intent(applicationContext, MainActivity::class.java)
                            startActivity(GoLogin)
                        } else{ // 회원 등록에 실패한 경우
                            Toast.makeText(applicationContext, "회원 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                            return
                        }
                    } catch (e : JSONException){
                        e.printStackTrace()
                    }
                }
            }
            // 서버로 Volley를 이용해서 요청
            val registerRequest = RegisterRequest(userID, userPass, userName, responseListener)
            val queue = Volley.newRequestQueue(applicationContext)
            queue.add(registerRequest)
        }
    }
}