package com.example.sampleappmvvm.di

import android.app.Application
import android.content.SharedPreferences
import com.example.sampleappmvvm.articleDetails.database.ArticlesCache
import com.example.sampleappmvvm.articleDetails.repository.ArticleDetailsRepository
import com.example.sampleappmvvm.articleDetails.ui.ArticleDetailsActivity
import com.example.sampleappmvvm.articleDetails.ui.HasArticleId
import com.example.sampleappmvvm.articlesList.repository.ArticlesRepository
import com.example.sampleappmvvm.login.repository.AuthRepository
import com.example.sampleappmvvm.login.ui.Loggable
import com.example.sampleappmvvm.login.ui.LoginActivity
import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.NetworkErrorHandler
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providePostRepository(
        apiManager: ApiManager,
        preferences: SharedPreferences,
        errorHandler: NetworkErrorHandler
    ): AuthRepository {
        return AuthRepository(apiManager, preferences, errorHandler)
    }

    @Provides
    fun provideArticlesRepository(
        apiManager: ApiManager,
        errorHandler: NetworkErrorHandler
    ): ArticlesRepository {
        return ArticlesRepository(apiManager, errorHandler)
    }

    @Provides
    fun provideArticleDetailsRepository(
        apiManager: ApiManager,
        errorHandler: NetworkErrorHandler,
        cache: ArticlesCache
    ): ArticleDetailsRepository {
        return ArticleDetailsRepository(apiManager, errorHandler, cache)
    }

    @Provides
    fun provideCache(application: Application): ArticlesCache {
        return ArticlesCache(application)
    }

    @Provides
    fun provideHttpClient(): ApiManager {
        return ApiManager()
    }

    @Provides
    fun provideArticleProvider(provider: ArticleDetailsActivity) : HasArticleId {
        return provider
    }

    @Provides
    fun provideShouldLogout(provider: LoginActivity): Loggable {
        return provider
    }
}