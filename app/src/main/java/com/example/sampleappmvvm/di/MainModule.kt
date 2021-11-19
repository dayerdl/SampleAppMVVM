package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.login.LoginRepository
import com.example.sampleappmvvm.server.ApiManager
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providePostRepository(apiManager: ApiManager): LoginRepository {
        return LoginRepository(apiManager)
    }

    @Provides
    fun provideHttpClient(): ApiManager {
        return ApiManager()
    }
}