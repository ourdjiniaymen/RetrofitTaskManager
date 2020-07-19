package com.example.retrofitTaskManager.data.network.implementation

import com.example.retrofitTaskManager.data.network.abstraction.Api
import com.example.retrofitTaskManager.data.network.abstraction.SERVER_URL
import com.example.retrofitTaskManager.data.network.response.AppTask
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

private const val BASE_URL: String = SERVER_URL + ""

interface TaskApi : Api {


    @GET("users/{id}/todos")
    suspend fun getUserTasks(
        @Path("id") id: Int
    ): Response<List<AppTask>>

    @Multipart
    @POST("todos")
    suspend fun addTask(
        @Part("userId") user:Int,
        @Part("title") title: String,
        @Part("completed") completed: Boolean
    )


    companion object {
        operator fun invoke(): TaskApi {

            return Api().baseUrl(BASE_URL)
                .build()
                .create(TaskApi::class.java)

        }
    }


}