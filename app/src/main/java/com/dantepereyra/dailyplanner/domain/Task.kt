package com.dantepereyra.dailyplanner.domain

import com.dantepereyra.dailyplanner.bd.taskDB.TaskEntity

data class Task(
    val id: Long?,
    val description: String,
    var isCompleted: Boolean = false,
    val date: Long?
)

fun Task.toEntity(date:Long) = id?.let {
    TaskEntity(
        id  = it,
        description = description,
        isCompleted = isCompleted,
        date = date
    )
}