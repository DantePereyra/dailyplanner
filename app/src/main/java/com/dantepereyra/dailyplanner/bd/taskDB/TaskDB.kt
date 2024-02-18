package com.dantepereyra.dailyplanner.bd.taskDB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.dantepereyra.dailyplanner.bd.Converters

const val DATABASE_VERSION = 1

@Database(entities = [TaskEntity::class], version = DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class TaskDBRoom : RoomDatabase() {
    abstract fun taskDao(): TaskDAO
}