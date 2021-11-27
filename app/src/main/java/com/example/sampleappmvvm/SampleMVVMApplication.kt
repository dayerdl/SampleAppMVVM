package com.example.sampleappmvvm

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.MasterKeys
import com.example.sampleappmvvm.di.AppComponent
import com.example.sampleappmvvm.di.DaggerAppComponent
import dagger.android.DaggerApplication

class SampleMVVMApplication : DaggerApplication() {

    override fun applicationInjector(): AppComponent {
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
        return appComponent
    }

}