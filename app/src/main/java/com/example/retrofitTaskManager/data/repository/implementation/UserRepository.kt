package com.example.retrofitTaskManager.data.repository.implementation

import com.example.retrofitTaskManager.data.entity.User
import com.example.retrofitTaskManager.data.network.response.AppUser
import com.example.retrofitTaskManager.data.network.implementation.UserApi
import com.example.retrofitTaskManager.data.repository.abstraction.SafeApiRequest
import java.net.PasswordAuthentication

class UserRepository (
    private val userApi: UserApi
): SafeApiRequest() {




    suspend fun login(email : String,password : String) : User? {
        return userApi.login(email,password).body()?.let { createUser(it[0])}
    }



    private fun createUser(appUser : AppUser) : User{
        return User(appUser.id,appUser.userName,appUser.email)
    }
}