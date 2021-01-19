package com.example.login_with_retrofit2.Helper

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val INTRO = "intro"
    private val NAME = "name"
    private val app_prefs : SharedPreferences
    private val context : Context

    init {
        app_prefs = context.getSharedPreferences("shared", 0)
        this.context = context
    }

    fun putIsLogin(loginOrOut : Boolean) {
        val edit = app_prefs.edit()
        edit.putBoolean(INTRO, loginOrOut)
        edit.apply()
    }

    fun putName(loginOrOut: String){
        val edit = app_prefs.edit()
        edit.putString(NAME, loginOrOut)
        edit.apply()
    }

    fun getName() : String? {
        return app_prefs.getString(NAME, "")
    }
}