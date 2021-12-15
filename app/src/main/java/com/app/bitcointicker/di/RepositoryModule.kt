package com.app.bitcointicker.di

import com.app.bitcointicker.data.network.ApiService
import com.app.bitcointicker.data.remoteDataSource.DataSourceImpl
import com.app.bitcointicker.data.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        apiService: ApiService,
    ): DataSourceImpl {
        return DataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideRepository(
        dataSource: DataSourceImpl,
    ): Repository {
        return Repository(dataSource)
    }
}