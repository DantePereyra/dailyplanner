package com.dantepereyra.dailyplanner.bd.taskDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDAO {
    @Query(" SELECT * FROM PilaEntity ")
    fun getAll(): List<TaskEntity>

    @Insert
    fun insertAll(vararg tasks: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)
}