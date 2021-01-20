package com.example.login_with_retrofit2.Interface

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterInterface {
    companion object{
        val REGIST_URL = "http://121.159.100.77/"
    }

    @FormUrlEncoded
    @POST("retrofit_simpleregister.php")
    fun getUserRegist(
            @Field("name") name : String,
            @Field("userName") userName : String,
            @Field("password") passwod : String
    ) : Call<String>
}