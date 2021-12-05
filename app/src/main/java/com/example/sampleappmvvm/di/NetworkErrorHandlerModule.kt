package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.server.NetworkErrorHandler
import dagger.Module
import dagger.Provides

@Module
class NetworkErrorHandlerModule {

    @Provides
    fun provideNetworkErrorHandler(): NetworkErrorHandler {
        return NetworkErrorHandler()
    }
}