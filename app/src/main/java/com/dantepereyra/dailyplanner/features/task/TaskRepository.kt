package com.dantepereyra.dailyplanner.features.task

import javax.inject.Inject

class TaskRepository @Inject constructor() {
    private val tasks = mutableListOf(
        Task(title = "Comida", description = "Preparar la lasaña y los entrantes"),
        Task( title = "Colada", description = "Poner lavadora y colgar ropa")
    )
    fun showTask(): List<Task> = tasks

    fun addTask(task: Task) {
        tasks.add(task)
    }
}
