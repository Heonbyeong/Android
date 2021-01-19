package com.example.login.Volley

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class LoginRequest(userID : String, userPassword : String, listener : Response.Listener<String>) : StringRequest(Method.POST, URL, listener, null) {
    private val map:Map<String, String>
    init{
        map = HashMap()
        map.put("userID", userID)
        map.put("userPassword", userPassword)
    }

    companion object {
        // 서버 url 설정 (php 연동)
        private val URL = "http://121.159.100.77/login.php"
    }

    @Throws(AuthFailureError::class)
    protected override fun getParams():Map<String, String> {
        return map
    }
}