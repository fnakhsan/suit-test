package com.akhsan.suittest.di

import com.akhsan.suittest.data.Repository
import com.akhsan.suittest.domain.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DataStoreModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provide(repository: Repository): UseCase
}