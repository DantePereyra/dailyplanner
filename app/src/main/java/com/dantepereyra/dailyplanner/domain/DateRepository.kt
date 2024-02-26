package com.dantepereyra.dailyplanner.domain

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun getSelectedDate(): String {
        return sharedPreferences.getString("selectedDate", getCurrentDate()) ?: getCurrentDate()
    }

    suspend fun saveSelectedDate(date: String) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().putString("selectedDate", date).apply()
        }
    }

    private fun getCurrentDate(): String {
        return SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Date())
    }
}
