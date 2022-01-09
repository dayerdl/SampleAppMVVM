package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.RxJavaActivity
import com.example.sampleappmvvm.articleDetails.ui.ArticleDetailsActivity
import com.example.sampleappmvvm.articlesList.view.ArticlesListActivity
import com.example.sampleappmvvm.login.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [FragmentBuilder::class, MainModule::class])
    abstract fun bindMainActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindArticlesListActivity(): ArticlesListActivity

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindArticleDetailsActivity(): ArticleDetailsActivity

    @ContributesAndroidInjector
    abstract fun bindRxJavaActivity(): RxJavaActivity

}