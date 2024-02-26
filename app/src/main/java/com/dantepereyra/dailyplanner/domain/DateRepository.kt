package com.dantepereyra.dailyplanner.domain

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DateRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val dateDataStore: DataStore<Preferences>
) {

    fun getSelectedDate(): String {
        return sharedPreferences.getString("selectedDate", getCurrentDate()) ?: getCurrentDate()
    }

    fun getSelectedDateFlow(): Flow<String> {
        return dateDataStore.data.map { preferencesMap: Preferences ->
            preferencesMap[stringPreferencesKey(
                "date"
            )] ?: getCurrentDate()
        }
    }

    fun saveSelectedDate(date: String) {
        sharedPreferences.edit().putString("selectedDate", date).apply()
        runBlocking {
            dateDataStore.edit { preferencesMap ->
                preferencesMap[stringPreferencesKey("date")] = date
            }
        }
    }

    private fun getCurrentDate(): String {
        val date = SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Date())
        saveSelectedDate(date)
        return date
    }

}
