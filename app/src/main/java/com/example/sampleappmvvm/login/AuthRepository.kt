package com.example.sampleappmvvm.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.annotation.RequiresApi
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.TokenRequest
import com.example.sampleappmvvm.server.TokenResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(private val apiManager: ApiManager) {

    lateinit var privatePreferences: SharedPreferences

    suspend fun generateToken(request: TokenRequest): TokenResponse {
        return apiManager.provideNoAuthClient().getToken(request)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun storeToken(context: Context, token: String) {
        val advancedSpec = KeyGenParameterSpec.Builder(
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).apply {
            setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            setKeySize(256)
            setUserAuthenticationRequired(true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                setUnlockedDeviceRequired(true)
                setIsStrongBoxBacked(true)
            }
        }.build()

        val advancedKeyAlias = MasterKeys.getOrCreate(advancedSpec)
        privatePreferences = EncryptedSharedPreferences.create(
            "secret_preferences",
            advancedKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        privatePreferences.edit { putString("TOKEN", token) }
    }

    fun getToken(): String? {
        return privatePreferences.getString("TOKEN","")
    }

}