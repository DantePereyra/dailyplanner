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
    fun markTaskAsCompleted(id: Long) {

    }

    fun deleteTask(id: Long) {

    }

    /******* Acceso a base de datos "manual" **********/
    fun getTasksSQLite(): List<Task> {
        val tasks = mutableListOf<Task>()
        val cursor = writableDB.query(
            TaskDBScheme.TABLE_NAME,
            arrayOf(
                TaskDBScheme.COLUMN_ID,
                TaskDBScheme.COLUMN_DESCRIPTION,
                TaskDBScheme.COLUMN_IS_COMPLETED,
                TaskDBScheme.COLUMN_DATE
            ),
            null, null, null, null, null
        )
        with(cursor) {
            while (moveToNext()) {
                tasks.add(
                    Task(
                        id = getLong(getColumnIndexOrThrow(TaskDBScheme.COLUMN_ID)),
                        description = getString(getColumnIndexOrThrow(TaskDBScheme.COLUMN_DESCRIPTION)),
                        isCompleted = getInt(getColumnIndexOrThrow(TaskDBScheme.COLUMN_IS_COMPLETED)) == 1,
                        date = getLong(getColumnIndexOrThrow(TaskDBScheme.COLUMN_DATE))
                    )
                )
                close()
            }
        }
        return tasks.toList()
    }

    /****** Guardar tareas mediante DBHelper ********/
    fun saveTaskSQLite(task: Task){
        val values = ContentValues().apply {
            put(TaskDBScheme.COLUMN_DESCRIPTION, task.description)
            put(TaskDBScheme.COLUMN_IS_COMPLETED, if (task.isCompleted) 1 else 0)
            put(TaskDBScheme.COLUMN_DATE, task.date)
        }
        writableDB.insert(TaskDBScheme.TABLE_NAME, null, values)
    }


}
