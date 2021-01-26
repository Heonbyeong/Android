package com.example.login_with_retrofit2.DataClass

data class UserInfo(
        val status: Boolean,
        val message: String,
        val user: User
)

data class User(
        val id: Int,
        val name: String,
        val userName: String,
        val password: String
)