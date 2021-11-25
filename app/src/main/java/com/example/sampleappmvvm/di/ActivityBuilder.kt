package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.articles.ArticlesListActivity
import com.example.sampleappmvvm.login.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    abstract fun bindMainActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [FragmentBuilder::class])
    abstract fun bindArticlesListActivity(): ArticlesListActivity
}