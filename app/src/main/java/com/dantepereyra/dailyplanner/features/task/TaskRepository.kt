package com.dantepereyra.dailyplanner.features.task

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import com.dantepereyra.dailyplanner.bd.TaskDBScheme
import com.dantepereyra.dailyplanner.bd.taskDB.TaskDAO
import com.dantepereyra.dailyplanner.bd.taskDB.toDomain
import com.dantepereyra.dailyplanner.domain.Task
import com.dantepereyra.dailyplanner.domain.toEntity
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val writableDB: SQLiteDatabase,
    private val taskDAO: TaskDAO
) {
    fun getTasksDao(): List<Task> = taskDAO.getAll().map { it.toDomain() }
    fun saveTaskDao(task: Task) = taskDAO.insertAll(task.toEntity())

    /******* Acceso a base de datos "manual" **********/
    fun getTasksSQLite(): List<Task> {
        val tasks = mutableListOf<Task>()
        val cursor = writableDB.query(
            TaskDBScheme.TABLE_NAME,
            arrayOf(
                TaskDBScheme.COLUMN_NAME,
                TaskDBScheme.COLUMN_DESCRIPTION
            ),
            null, null, null, null, null
        )
        with(cursor) {
            while (moveToNext()) {
                tasks.add(
                    Task(
                        tittle = getString(getColumnIndexOrThrow(TaskDBScheme.COLUMN_NAME)),
                        description = getString(getColumnIndexOrThrow(TaskDBScheme.COLUMN_DESCRIPTION))
                    )
                )
                close()
            }
        }
        return tasks.toList()
    }

    /****** Guardar pilas mediante DBHelper ********/
    fun saveTaskSQLite(task: Task){
        val values = ContentValues().apply {
            put(TaskDBScheme.COLUMN_NAME,task.tittle)
            put(TaskDBScheme.COLUMN_DESCRIPTION, task.description)
        }
        writableDB.insert(TaskDBScheme.TABLE_NAME,null,values)
    }

}
