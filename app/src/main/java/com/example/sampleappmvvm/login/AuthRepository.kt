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

class AuthRepository @Inject constructor(
    private val apiManager: ApiManager,
    private val privatePreferences: SharedPreferences
) {

    suspend fun generateToken(request: TokenRequest): TokenResponse {
        return apiManager.provideNoAuthClient().getToken(request)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun storeToken(token: String) {
        privatePreferences.edit { putString("TOKEN", token) }
    }

    fun getToken(): String? {
        return privatePreferences.getString("TOKEN", null)
    }

}