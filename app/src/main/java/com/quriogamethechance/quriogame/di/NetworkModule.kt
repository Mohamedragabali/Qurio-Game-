package com.quriogamethechance.quriogame.di

import com.quriogamethechance.quriogame.data.remote.GameApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://opentdb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideGameApiService(retrofit : Retrofit): GameApiService =
        retrofit.create(GameApiService::class.java)
}