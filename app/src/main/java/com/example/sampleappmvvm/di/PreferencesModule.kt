package com.example.sampleappmvvm.di

import android.app.Application
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.sampleappmvvm.SampleMVVMApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferencesModule {

    @Provides
    fun providesContext(application: SampleMVVMApplication): Application {
        return application
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(application: SampleMVVMApplication): SharedPreferences {
        return EncryptedSharedPreferences.create(
            "secret_preferences",
            createMasterKey(),
            application,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private fun createMasterKey(): String {
        val advancedSpec = KeyGenParameterSpec.Builder(
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            setKeySize(256)
            setUserAuthenticationRequired(false)
        }.build()
        return MasterKeys.getOrCreate(advancedSpec)
    }
}
