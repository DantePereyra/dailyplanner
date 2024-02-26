package com.dantepereyra.dailyplanner.features.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dantepereyra.dailyplanner.domain.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    private var _state = MutableStateFlow(emptyList<Task>())
    val state: StateFlow<List<Task>> = _state

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _state.value = repository.getTasksDao()

            }
        }
    }
    fun markTaskAsCompleted(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.markTaskAsCompleted(task.id)

                _state.value = repository.getTasksDao()
            }
        }
    }
    fun deleteTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteTask(task.id)
                // Actualizar el estado con la lista de tareas actualizada
                _state.value = repository.getTasksDao()
            }
        }
    }
    fun editTask(task: Task) {
        // Implementar la lógica para editar una tarea
        // abriendo una pantalla nueva
    }

    suspend fun getCatFact():String{
        return repository.getCatFacts()
    }




}

