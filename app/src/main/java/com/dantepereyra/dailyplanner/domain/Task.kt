package com.dantepereyra.dailyplanner.domain

import com.dantepereyra.dailyplanner.bd.taskDB.TaskEntity

data class Task(
    val tittle: String,
    val description: String,
)

fun Task.toEntity() = TaskEntity(
    tittle = tittle,
    description = description
)