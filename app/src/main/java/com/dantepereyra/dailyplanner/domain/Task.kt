package com.dantepereyra.dailyplanner.domain

import com.dantepereyra.dailyplanner.bd.taskDB.TaskEntity

data class Task(
    val id: Long,
    val description: String,
    var isCompleted: Boolean = false,
    val date: Long
)

fun Task.toEntity() = TaskEntity(
    id  = id,
    description = description,
    isCompleted = isCompleted,
    date = date
)