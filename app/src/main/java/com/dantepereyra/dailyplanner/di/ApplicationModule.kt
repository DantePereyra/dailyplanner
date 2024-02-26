package com.dantepereyra.dailyplanner.di

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

val Context.dateDataStore: DataStore<Preferences> by preferencesDataStore(name = "date")

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {


    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("date", Context.MODE_PRIVATE)
    }

    @Provides
    fun providesDateDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dateDataStore
    }
}
