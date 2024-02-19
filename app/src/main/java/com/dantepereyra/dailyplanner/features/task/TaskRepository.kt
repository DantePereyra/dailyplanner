package com.dantepereyra.dailyplanner.features.task

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.dantepereyra.dailyplanner.api.CatsApi
import com.dantepereyra.dailyplanner.api.FactResponse
import com.dantepereyra.dailyplanner.bd.TaskDBScheme
import com.dantepereyra.dailyplanner.bd.taskDB.TaskDAO
import com.dantepereyra.dailyplanner.bd.taskDB.toDomain
import com.dantepereyra.dailyplanner.domain.Task
import com.dantepereyra.dailyplanner.domain.toEntity

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import kotlinx.serialization.json.Json

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val writableDB: SQLiteDatabase,
    private val taskDAO: TaskDAO,
    private val api: CatsApi
) {
    fun getTasksDao(): List<Task> = taskDAO.getAll().map { it.toDomain() }
    fun saveTaskDao(task: Task) = taskDAO.insertAll(task.toEntity())
    fun markTaskAsCompleted(id: Long) {

        taskDAO.markTaskAsCompleted(id)

    }

    fun deleteTask(id: Long) {
        taskDAO.getTasksByID(id)
    }

    suspend fun getCatFacts(): String {
        return withContext(Dispatchers.IO) {

            val fact: FactResponse = api.getCatFact().await()
            fact.text
        }
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
    fun saveTaskSQLite(task: Task) {
        val values = ContentValues().apply {
            put(TaskDBScheme.COLUMN_DESCRIPTION, task.description)
            put(TaskDBScheme.COLUMN_IS_COMPLETED, if (task.isCompleted) 1 else 0)
            put(TaskDBScheme.COLUMN_DATE, task.date)
        }
        writableDB.insert(TaskDBScheme.TABLE_NAME, null, values)
    }


}
