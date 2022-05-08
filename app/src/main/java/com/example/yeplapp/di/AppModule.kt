package com.example.yeplapp.di

import com.example.yeplapp.application.AppConstants
import com.example.yeplapp.repository.WebService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
        .baseUrl(AppConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(AppConstants.okHttp.build())
        .build()

    @Singleton
    @Provides
    fun provideBusinessService(retrofit: Retrofit): WebService = retrofit.create(WebService::class.java)

}