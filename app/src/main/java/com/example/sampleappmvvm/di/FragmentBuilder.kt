package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.articlesList.view.ArticlesListFragment
import com.example.sampleappmvvm.articlesList.viewmodel.OnArticleClickListener
import com.example.sampleappmvvm.login.ui.LoginFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainFragment(): LoginFragment

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindArticlesFragment(): ArticlesListFragment
}