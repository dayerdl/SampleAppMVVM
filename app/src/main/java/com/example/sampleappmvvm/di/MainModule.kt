package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.domain.Repository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
class MainModule {

    companion object {
        const val NAME_IO_SCHEDULER = "ioScheduler"
        const val NAME_MAIN_THREAD_SCHEDULER = "mainThreadScheduler"
    }

    @Provides
    fun providePostRepository(apiManager: ApiManager): Repository {
        return Repository(apiManager)
    }

    @Provides
    fun provideHttpClient(): ApiManager {
        return ApiManager()
    }

    @Provides
    @Named(NAME_IO_SCHEDULER)
    fun provideiOThread(): Scheduler {
        return Schedulers.io()
    }

    @Provides
    @Named(NAME_MAIN_THREAD_SCHEDULER)
    fun provideMainThread(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}