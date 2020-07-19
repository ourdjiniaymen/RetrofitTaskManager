package com.example.retrofitTaskManager.data.repository.abstraction

import android.util.Log
import com.example.retrofitTaskManager.data.internal.GetDataFromApiException
import retrofit2.Response


abstract class SafeApiRequest {

        suspend fun <T : Any> apiRequest(call : suspend () -> Response<T>) : T {
            val response = call.invoke()

            if( response.isSuccessful){
                Log.d("Debug" , response.body().toString())
                return response.body()!!
            }
            else {
                throw GetDataFromApiException(response.code().toString())
            }
        }
}