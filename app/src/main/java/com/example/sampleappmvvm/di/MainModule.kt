package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.articles.domain.ArticlesRepository
import com.example.sampleappmvvm.login.AuthRepository
import com.example.sampleappmvvm.server.ApiManager
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providePostRepository(apiManager: ApiManager): AuthRepository {
        return AuthRepository(apiManager)
    }

    @Provides
    fun provideArticlesRepository(apiManager: ApiManager): ArticlesRepository {
        return ArticlesRepository(apiManager)
    }

    @Provides
    fun provideHttpClient(): ApiManager {
        return ApiManager()
    }
}