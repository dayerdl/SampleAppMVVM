package com.example.sampleappmvvm.di

import com.example.sampleappmvvm.SampleMVVMApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@Component(modules = [ActivityBuilder::class, FragmentBuilder::class, AndroidSupportInjectionModule::class,
    PreferencesModule::class, NetworkErrorHandlerModule::class])
interface AppComponent : AndroidInjector<SampleMVVMApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SampleMVVMApplication): Builder
        fun build(): AppComponent
    }
}