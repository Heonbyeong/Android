package com.example.login_with_retrofit2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.example.login_with_retrofit2.Helper.PreferenceHelper
import com.example.login_with_retrofit2.Interface.RegisterInterface
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferenceHelper = PreferenceHelper(this)

        tvlogin.setOnClickListener{
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn.setOnClickListener{
            registerMe()
        }
    }

    private fun registerMe(){
        val name = etname.text.toString()
        val userName = etusername.text.toString()
        val password = etpassword.text.toString()

        val retrofit = Retrofit.Builder()
                .baseUrl(RegisterInterface.REGIST_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()

        val api = retrofit.create(RegisterInterface::class.java)
        val call = api.getUserRegist(name,userName,password)
        call.enqueue(CallBack<String>(){
            override fun onResponse(@NonNull call: Call<String>, @NonNull response: Response<String>){
                if(response.isSuccessful() && response.body() != null){
                    Log.e("onSuccess", response.body()!!)

                    val jsonResponse = response.body()
                    try{
                        parseRegData(jsonResponse)
                    } catch (e: JSONException){
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(@NonNull call : Call<String>, @NonNull t : Throwable) {
                Log.e(TAG, "에러 = " + t.getMessage())
            }
        }
        })
    }
