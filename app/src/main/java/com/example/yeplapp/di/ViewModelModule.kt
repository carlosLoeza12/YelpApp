package com.example.yeplapp.di

import com.example.yeplapp.repository.BusinessRepository
import com.example.yeplapp.repository.BusinessRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindRepoImpl(businessRepositoryImpl: BusinessRepositoryImpl): BusinessRepository

}