package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.ui.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainFragment(): MainFragment
}