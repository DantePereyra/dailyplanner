package com.dantepereyra.dailyplanner.di

import com.dantepereyra.dailyplanner.api.CatsApi
import com.dantepereyra.dailyplanner.api.FactResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun providesLoggerInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }

    @Provides
    fun providesOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return client
    }

    @Provides
    fun providesCatService(client: OkHttpClient): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }

    @Provides
    fun providesCatApi(retrofit: Retrofit): CatsApi {
        val api = retrofit.create(CatsApi::class.java)
        return api
    }

}