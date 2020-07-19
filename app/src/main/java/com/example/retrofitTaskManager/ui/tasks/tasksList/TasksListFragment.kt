package com.example.retrofitTaskManager.ui.tasks.tasksList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitTaskManager.R
import com.example.retrofitTaskManager.ui.accessibility.login.LoginViewModel
import kotlinx.android.synthetic.main.tasks_list_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class TasksListFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModel: TasksListViewModel by instance()
    private val adapter = TaskAdapter()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tasks_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.tasks_nav_fragment)
        initializeUi()
        add_task.setOnClickListener {
            navController.navigate(R.id.to_add_task_fragment_action)
        }
    }

    private fun initializeUi() {
        //set recycle view adapter
        val recyclerView: RecyclerView = list_tasks as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        val loginViewModel: LoginViewModel by instance()
        CoroutineScope(Dispatchers.IO).launch {
            loginViewModel.user?.id?.let { viewModel.getTasks(it) }
        }
        viewModel.tasks.observe(viewLifecycleOwner, Observer { tasks ->
            adapter.setTasks(tasks)
        })

    }
}
