package com.dantepereyra.dailyplanner.bd.taskDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDAO {
    @Query(" SELECT * FROM TaskEntity ")
    fun getAll(): List<TaskEntity>

    @Insert
    fun insertAll(vararg tasks: TaskEntity)

    @Delete
    fun delete(task: TaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE date = :date")
    fun getTasksByDate(date: Long): List<TaskEntity>
}