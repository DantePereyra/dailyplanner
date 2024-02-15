package com.dantepereyra.dailyplanner.di

import android.content.Context
import androidx.room.Room
import com.dantepereyra.dailyplanner.bd.taskDB.TaskDBRoom
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
object TaskDBModule {

    //   @Provides
    //   fun providesTaskDBHelper(@ApplicationContext context: Context): SQLiteDatabase {
    //       return TaskDBHelper(context).writableDatabase


    const val DATABASE_NAME = "TasksRoom.db"

    @Provides
    fun providesTaskDbRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        TaskDBRoom::class.java, DATABASE_NAME
    ).build()

    @Provides
    fun providesTaskDao(taskDBRoom: TaskDBRoom) = taskDBRoom.taskDao()
}
