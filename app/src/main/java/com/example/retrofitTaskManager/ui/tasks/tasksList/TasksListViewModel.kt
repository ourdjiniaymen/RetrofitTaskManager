package com.example.retrofitTaskManager.ui.tasks.tasksList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitTaskManager.data.entity.Task
import com.example.retrofitTaskManager.data.repository.implementation.TaskRepository
import com.example.retrofitTaskManager.utilities.Coroutines
import kotlinx.coroutines.Job

class TasksListViewModel(
    private val taskRepository : TaskRepository
) : ViewModel() {

    private lateinit var job : Job
    private val _tasks = MutableLiveData<List<Task>>()


    val tasks : LiveData<List<Task>>
        get() = _tasks


    fun getTasks(user : Int) {

        job = Coroutines.ioThMain(
            {taskRepository.getUserPosts(user)},
            {_tasks.value = it}
        )
    }


    override fun onCleared() {
        if(::job.isInitialized){
            job.cancel()
        }
    }
}