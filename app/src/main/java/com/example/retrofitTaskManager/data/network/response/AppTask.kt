package com.example.retrofitTaskManager.data.network.response

import com.google.gson.annotations.SerializedName

data class AppTask(
    @SerializedName("id")  val id: Int,
    @SerializedName("userId")  val user: String,
    @SerializedName("title") val title: String,
    @SerializedName("completed") val completed: Boolean
)