package com.example.retrofitTaskManager.ui.tasks.tasksList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitTaskManager.R
import com.example.retrofitTaskManager.data.entity.Task
import java.util.*
import kotlin.collections.ArrayList

@Suppress("UNCHECKED_CAST")
class TaskAdapter : RecyclerView.Adapter<TaskAdapter.TaskHolder>(),
    Filterable {

    private var tasks: MutableList<Task> = ArrayList()
    private var tasksFull: MutableList<Task> = ArrayList()
    private var listener: OnItemLongClickListener? = null
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): TaskHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item, parent, false)
        return TaskHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull holder: TaskHolder, position: Int) {
        val currentTask = tasks[position]
        holder.task.text = currentTask.title
        holder.state.text = if (currentTask.completed){
            "Complete"
        }else{
            "Incomplete"
        }


    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun setTasks(tasks: List<Task>) {
        this.tasks = tasks as MutableList<Task>
        this.tasksFull = ArrayList(tasks)
        notifyDataSetChanged()
    }

    fun getTaskAt(position: Int): Task? {
        return tasks[position]
    }

    inner class TaskHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal val task: TextView = itemView.findViewById(R.id.task)
        internal val state: TextView = itemView.findViewById(R.id.state)


        init {

            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemLongClick(position)
                }
                true
            }
        }
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener?) {
        this.listener = listener
    }

    override fun getFilter(): Filter {
        return tasksFilter
    }

    private val tasksFilter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Task> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(tasksFull)
            } else {
                val filterPattern =
                    constraint.toString().toLowerCase(Locale.ROOT).trim { it <= ' ' }
                for (item in tasksFull) {
                    if (item.completed) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(
            constraint: CharSequence,
            results: FilterResults
        ) {
            tasks.clear()
            tasks.addAll(results.values as List<Task>)
            notifyDataSetChanged()
        }
    }


}