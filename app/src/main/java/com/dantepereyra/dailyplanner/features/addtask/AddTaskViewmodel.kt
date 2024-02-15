package com.dantepereyra.dailyplanner.features.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dantepereyra.dailyplanner.domain.Task
import com.dantepereyra.dailyplanner.features.task.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class AddTaskViewmodel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    fun saveTask(task: Task){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.saveTaskDao(task)
            }
        }
    }
}