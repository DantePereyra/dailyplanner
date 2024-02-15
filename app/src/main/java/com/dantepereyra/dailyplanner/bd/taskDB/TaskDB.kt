package com.dantepereyra.dailyplanner.bd.taskDB

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_VERSION = 1

@Database(entities = [TaskEntity::class], version = DATABASE_VERSION)
abstract class TaskDBRoom : RoomDatabase() {
    abstract fun taskDao(): TaskDAO
}