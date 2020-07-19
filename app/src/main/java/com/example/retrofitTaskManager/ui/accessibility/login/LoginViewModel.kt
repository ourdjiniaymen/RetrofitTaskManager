package com.example.retrofitTaskManager.ui.accessibility.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitTaskManager.data.entity.User
import com.example.retrofitTaskManager.data.repository.implementation.UserRepository
import com.example.retrofitTaskManager.utilities.Coroutines
import kotlinx.coroutines.Job

class LoginViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private lateinit var job : Job
    private var _user : User? = null


    val user : User?
        get() = _user


    fun login(email : String,password : String) {

        job = Coroutines.ioThMain(
            {userRepository.login(email,password)},
            {_user = it}
        )
    }


    override fun onCleared() {
        if(::job.isInitialized){
            job.cancel()
        }
    }
}