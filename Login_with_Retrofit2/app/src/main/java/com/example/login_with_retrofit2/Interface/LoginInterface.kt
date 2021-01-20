package com.example.login_with_retrofit2.Interface

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginInterface {
    companion object{
        val LOGIN_URL = "http://121.159.100.77/"
    }
    @FormUrlEncoded
    @POST("retrofit_simplelogin.php")
    fun getUserLogin(
            @Field("userName") userName : String,
            @Field("password") password : String
    ) : Call<String>
}