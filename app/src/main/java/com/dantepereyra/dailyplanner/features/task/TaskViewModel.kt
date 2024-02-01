package com.dantepereyra.dailyplanner.features.task

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    var _state : List<Task> = emptyList()
    val state : List<Task>
        get() = _state
    init{
        refreshTasks()
    }
    private fun refreshTasks() {
        _state = repository.showTask()
    }
    fun addTask(title: String,description: String){
        val newTask = Task(title = title, description = description)
        repository.addTask(newTask)
        refreshTasks()
    }

}

data class Task(
    val title: String,
    val description: String,
)