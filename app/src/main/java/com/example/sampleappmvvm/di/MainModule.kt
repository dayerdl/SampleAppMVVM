package com.example.sampleappmvvm.di

import android.app.Application
import android.content.SharedPreferences
import com.example.sampleappmvvm.articleDetails.database.ArticlesCache
import com.example.sampleappmvvm.articleDetails.domain.ArticleDetailsRepository
import com.example.sampleappmvvm.articlesList.domain.ArticlesRepository
import com.example.sampleappmvvm.login.AuthRepository
import com.example.sampleappmvvm.server.ApiManager
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providePostRepository(
        apiManager: ApiManager,
        preferences: SharedPreferences
    ): AuthRepository {
        return AuthRepository(apiManager, preferences)
    }

    @Provides
    fun provideArticlesRepository(apiManager: ApiManager): ArticlesRepository {
        return ArticlesRepository(apiManager)
    }

    @Provides
    fun provideArticleDetailsRepository(
        apiManager: ApiManager,
        cache: ArticlesCache
    ): ArticleDetailsRepository {
        return ArticleDetailsRepository(apiManager, cache)
    }

    @Provides
    fun provideCache(application: Application): ArticlesCache {
        return ArticlesCache(application)
    }

    @Provides
    fun provideHttpClient(): ApiManager {
        return ApiManager()
    }
}