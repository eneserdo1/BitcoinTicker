package com.app.bitcointicker.di

import com.app.bitcointicker.data.local.LocalDataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataManager {

    @Singleton
    @Provides
    fun provideLocalDataManager() = LocalDataManager()


}