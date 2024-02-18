package com.dantepereyra.dailyplanner.bd.taskDB

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dantepereyra.dailyplanner.domain.Task


@Entity
class TaskEntity(

    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "is_Completed") var isCompleted: Boolean = false,
    @ColumnInfo(name = "date") val date: Long,

    )
fun TaskEntity.toDomain() = Task(
    id  = id,
    description = description,
    isCompleted = isCompleted,
    date = date

)
