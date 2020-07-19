package com.example.retrofitTaskManager.data.entity

data class Task(
    val id : Int,
    val user : Int,
    val title : String,
    val completed : Boolean
)