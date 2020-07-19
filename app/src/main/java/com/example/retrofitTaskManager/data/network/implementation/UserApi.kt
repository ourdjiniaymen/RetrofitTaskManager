package com.example.retrofitTaskManager.data.network.implementation

import com.example.retrofitTaskManager.data.network.abstraction.Api
import com.example.retrofitTaskManager.data.network.abstraction.SERVER_URL
import com.example.retrofitTaskManager.data.network.response.AppUser
import retrofit2.Response
import retrofit2.http.*

private const val BASE_URL: String = SERVER_URL + ""

interface UserApi : Api {

    @GET("users")
    suspend fun login(
        @Query("email") email : String,
        @Query("username") password : String
    ): Response<List<AppUser>>

    companion object {
        operator fun invoke(): UserApi {

            return Api().baseUrl(BASE_URL)
                .build()
                .create(UserApi::class.java)

        }
    }
}