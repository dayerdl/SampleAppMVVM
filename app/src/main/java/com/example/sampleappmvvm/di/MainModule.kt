package com.example.sampleappmvvm.di

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.sampleappmvvm.SampleMVVMApplication
import com.example.sampleappmvvm.articles.domain.ArticlesRepository
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
    fun provideHttpClient(): ApiManager {
        return ApiManager()
    }
}