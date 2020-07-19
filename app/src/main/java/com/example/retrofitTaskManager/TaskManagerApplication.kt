package com.example.retrofitTaskManager

import android.app.Application
import com.example.retrofitTaskManager.data.network.implementation.TaskApi
import com.example.retrofitTaskManager.data.network.implementation.UserApi
import com.example.retrofitTaskManager.data.repository.implementation.TaskRepository
import com.example.retrofitTaskManager.data.repository.implementation.UserRepository
import com.example.retrofitTaskManager.ui.accessibility.login.LoginViewModel
import com.example.retrofitTaskManager.ui.tasks.tasksList.TasksListViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class TaskManagerApplication : Application() , KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@TaskManagerApplication))

        bind() from  singleton { TaskApi.invoke() }
        bind() from  singleton { TaskRepository(instance()) }
        bind<TasksListViewModel>() with singleton { TasksListViewModel(instance()) }

        bind() from  singleton { UserApi.invoke() }
        bind() from  singleton { UserRepository(instance()) }
        bind<LoginViewModel>() with singleton { LoginViewModel(instance()) }
    }


}