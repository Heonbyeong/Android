package com.example.login_with_retrofit2.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.login_with_retrofit2.Helper.PreferenceHelper
import com.example.login_with_retrofit2.Interface.RegisterInterface
import com.example.login_with_retrofit2.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var preferenceHelper: PreferenceHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferenceHelper = PreferenceHelper(this)

        tvlogin.setOnClickListener{
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnRegist.setOnClickListener{
            registerMe()
        }
    }

    private fun registerMe(){
        val name = etname.text.toString()
        val userName = etusername.text.toString()
        val password = etpassword.text.toString()

        val gson = GsonBuilder()
                .setLenient()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(RegisterInterface.REGIST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

        val api = retrofit.create(RegisterInterface::class.java)
        val call = api.getUserRegist(name, userName, password)
        call.enqueue(object : Callback<String>{

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful() && response.body() != null){
                    Log.e("onSuccess", response.body().toString())
                    val jsonResponse = response.body()
                    try {
                        if (jsonResponse != null) {
                            parseRegData(jsonResponse)
                        }
                    } catch (e : JSONException){
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.e(TAG, "에러 = " + t.message)
            }
        })
    }

    @Throws(JSONException::class)
    private fun parseRegData(response: String){
        val jsonObject = JSONObject(response.toString())
        if(jsonObject.optString("status").equals("true")){
            saveInfo(response)
            Toast.makeText(applicationContext, "회원가입 성공", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext, jsonObject.getString("message"), Toast.LENGTH_SHORT).show()

        }
    }

    private fun saveInfo(response: String){
        preferenceHelper.putIsLogin(true)
        try {
            val jsonObject = JSONObject(response.toString())
            if(jsonObject.getString("status").equals("true")){
                val dataArray = jsonObject.getJSONArray("data")
                for(i in 0 until dataArray.length()){
                    val dataObj = dataArray.getJSONObject(i)
                    preferenceHelper.putName(dataObj.getString("name"))
                }
            }
        } catch (e : JSONException){
            e.printStackTrace()
        }
    }
}

