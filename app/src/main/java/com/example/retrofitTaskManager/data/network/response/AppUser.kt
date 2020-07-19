package com.example.retrofitTaskManager.data.network.response

import com.google.gson.annotations.SerializedName
data class AppUser(
@SerializedName("id")  val id: Int,
@SerializedName("username")  val userName: String,
@SerializedName("email") val email: String,
@SerializedName("password") val password: String
)