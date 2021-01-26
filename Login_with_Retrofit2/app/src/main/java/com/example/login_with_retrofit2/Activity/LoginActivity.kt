package com.example.login_with_retrofit2.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.login_with_retrofit2.Helper.PreferenceHelper
import com.example.login_with_retrofit2.Interface.LoginInterface
import com.example.login_with_retrofit2.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.login_activity.*
import org.json.JSONException

import org.json.JSONObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class LoginActivity : AppCompatActivity() {
    private val TAG = "LoginActivity"
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        preferenceHelper = PreferenceHelper(this)

        tvreg.setOnClickListener{
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnlogin.setOnClickListener{
            loginUser()
        }
    }

    private fun loginUser(){
        val userName = etusername2.text.toString()
        val password = etpassword2.text.toString()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(LoginInterface.LOGIN_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

        val api = retrofit.create(LoginInterface::class.java)
        val call = api.getUserLogin(userName, password)
        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful() && response.body() != null){
                    Log.e("onSuccess", response.body().toString())

                    val jsonResponse = response.body()
                    parseLoginData(jsonResponse)

                    val intent = Intent(applicationContext, LoginSuccessActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "에러 = " + t.message)
            }
        })
    }

    private fun parseLoginData(response: String?){
        try {
            val jsonObject  = JSONObject(response.toString())
            if (jsonObject.getString("status").equals("true")){
                saveInfo(response)

                Toast.makeText(applicationContext, "Login Successfully!", Toast.LENGTH_SHORT).show()
            }
        } catch (e : JSONException){
            e.printStackTrace()
        }
    }

    private fun saveInfo(response: String?){
        preferenceHelper.putIsLogin(true)
        try {
            val jsonObject = JSONObject(response.toString())
            if (jsonObject.getString("status").equals("true")){
                val dataArray = jsonObject.getJSONArray("data")
                for (i in 0 until dataArray.length()){
                    val dataObj = dataArray.getJSONObject(i)
                    preferenceHelper.putName(dataObj.getString("name"))
                }
            }
        } catch (e: JSONException){
            e.printStackTrace()
        }
    }
}