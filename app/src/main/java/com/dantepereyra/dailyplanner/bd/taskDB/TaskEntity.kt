package com.dantepereyra.dailyplanner.bd.taskDB

import android.icu.text.CaseMap.Title
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dantepereyra.dailyplanner.domain.Task


@Entity
class TaskEntity(

    @PrimaryKey val tittle: String,
    @ColumnInfo(name = "description") val description: String,

    )
fun TaskEntity.toDomain() = Task(
    tittle  = tittle,
    description = description

)