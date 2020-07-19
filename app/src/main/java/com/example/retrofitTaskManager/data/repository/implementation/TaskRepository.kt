package com.example.retrofitTaskManager.data.repository.implementation


import com.example.retrofitTaskManager.data.network.response.AppTask
import com.example.retrofitTaskManager.data.network.implementation.TaskApi
import com.example.retrofitTaskManager.data.repository.abstraction.SafeApiRequest
import com.example.retrofitTaskManager.data.entity.Task


class TaskRepository (
    private val taskApi: TaskApi
): SafeApiRequest() {



    suspend fun addTask(title : String, user : Int) {
        taskApi.addTask(user,title,false)
    }





    suspend fun getUserPosts(user: Int) : ArrayList<Task> {
        return createTasks(taskApi.getUserTasks(user).body() as ArrayList<AppTask>)
    }



    private fun createTasks(appTasks : ArrayList<AppTask>) : ArrayList<Task>{


        val tasks = ArrayList<Task>()

        for (appTask in appTasks) {

            tasks.add(
                Task(
                    id = appTask.id,
                    title = appTask.title,
                    completed = appTask.completed,
                    user = appTask.id
                )
            )
        }

        return tasks
    }
}