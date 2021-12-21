package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.server.NetworkErrorHandler
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkErrorHandlerModule {
    @Singleton
    @Provides
    fun provideNetworkErrorHandler(): NetworkErrorHandler {
        return NetworkErrorHandler()
    }
}