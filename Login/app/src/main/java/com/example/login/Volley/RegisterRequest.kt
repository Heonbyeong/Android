package com.example.login.Volley

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest

class RegisterRequest(userID : String, userPassword : String, userName : String, listener : Response.Listener<String>) : StringRequest(Method.POST, URL, listener, null) {
    private val map : Map<String, String>
    init {
        map = HashMap()
        map.put("userID", userID)
        map.put("userPassword", userPassword)
        map.put("userName", userName)
    }
    companion object {
        // 서버 url 설정 (php 연동)
        private val URL = "http://121.159.100.77/Register.php"
    }

    @Throws(AuthFailureError::class)
    protected override fun getParams():Map<String, String> {
        return map
    }
}