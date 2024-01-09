package com.dantepereyra.dailyplanner

import androidx.lifecycle.ViewModel
import com.dantepereyra.dailyplanner.data.model.Task

class TaskViewModel: ViewModel() {
    val taskList = mutableListOf<Task>()
}