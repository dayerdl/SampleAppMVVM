package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.SampleMVVMApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [ActivityBuilder::class, AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<SampleMVVMApplication> {
}