package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.domain.Repository
import com.example.sampleappmvvm.server.ApiManager
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providePostRepository(apiManager: ApiManager): Repository {
        return Repository(apiManager)
    }

    @Provides
    fun provideHttpClient(): ApiManager {
        return ApiManager()
    }
}