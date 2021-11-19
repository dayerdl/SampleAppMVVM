package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.login.ui.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainFragment(): LoginFragment
}